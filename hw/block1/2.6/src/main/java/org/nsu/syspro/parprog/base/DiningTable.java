package org.nsu.syspro.parprog.base;

import org.nsu.syspro.parprog.interfaces.Fork;
import org.nsu.syspro.parprog.interfaces.Philosopher;

import java.util.ArrayList;

public abstract class DiningTable<P extends Philosopher, F extends Fork> {
    private final ArrayList<F> forks;
    private final ArrayList<P> phils;
    private final ArrayList<Thread> threads;

    private boolean started;
    private volatile boolean shouldStop;

    public DiningTable(int N) {
        if (N < 2) {
            throw new IllegalStateException("Too small dining table");
        }

        started = false;
        forks = new ArrayList<>(N);
        phils = new ArrayList<>(N);
        threads = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            forks.add(createFork());
            phils.add(createPhilosopher());
        }
    }

    public synchronized void start() {
        if (started) {
            throw new IllegalStateException("Restart is not supported");
        }

        shouldStop = false;
        final int N = phils.size();
        for (int i = 0; i < N; i++) {
            final Philosopher p = phils.get(i);
            final Fork left = forks.get(i);
            final Fork right = forks.get((i + 1) % N);
            final Thread t = new Thread(() -> {
                while (!shouldStop) {
                    p.onHungry(left, right);
                }
            });
            t.start();
            threads.add(t);
        }

        started = true;
    }

    public synchronized void stop() {
        if (shouldStop) {
            throw new IllegalStateException("Repeated stop is illegal");
        }

        if (!started) {
            throw new IllegalStateException("Start first");
        }

        shouldStop = true;
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public P philosopherAt(int index) {
        return phils.get(index);
    }

    public F forkAt(int index) {
        return forks.get(index);
    }

    public long maxMeals() {
        return phils.stream()
                .mapToLong(Philosopher::meals)
                .max()
                .getAsLong();
    }

    public long minMeals() {
        return phils.stream()
                .mapToLong(Philosopher::meals)
                .min()
                .getAsLong();
    }

    public long totalMeals() {
        return phils.stream()
                .mapToLong(Philosopher::meals)
                .sum();
    }

    public abstract F createFork();

    public abstract P createPhilosopher();
}
