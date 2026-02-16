package org.nsu.syspro.parprog.counters.impls;

public class ThreadUnsafeCounter implements Counter {

    private long data;

    public ThreadUnsafeCounter() {
        data = 0;
    }

    @Override
    public void increment() {
        data++;
    }

    @Override
    public long get() {
        return data;
    }
}
