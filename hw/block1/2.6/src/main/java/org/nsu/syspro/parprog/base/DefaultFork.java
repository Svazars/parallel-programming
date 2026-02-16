package org.nsu.syspro.parprog.base;

import org.nsu.syspro.parprog.interfaces.Fork;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultFork implements Fork {
    private static final AtomicLong idProvider = new AtomicLong(0);

    private final ReentrantLock lock = new ReentrantLock();
    private final long id;
    private volatile Thread owner;

    public DefaultFork() {
        this.id = idProvider.getAndAdd(1);
        this.owner = null;
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public void acquire() {
        lock.lock();
        final Thread thread = Thread.currentThread();
        if (owner != null) {
            throw new IllegalStateException(thread + " tries to acquire fork " + this + " which is already used by " + owner);
        }
        owner = thread;
    }

    @Override
    public void release() {
        final Thread thread = Thread.currentThread();
        final Thread currentOwner = owner;
        if (currentOwner != thread) {
            throw new IllegalStateException(thread + " tries to release fork " + this + " which is already used by " + currentOwner);
        }
        owner = null;
        lock.unlock();
    }

    @Override
    public String toString() {
        return String.format("Fork(%d)", id);
    }
}
