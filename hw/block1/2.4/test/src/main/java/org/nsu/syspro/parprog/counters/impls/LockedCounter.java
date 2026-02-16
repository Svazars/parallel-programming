package org.nsu.syspro.parprog.counters.impls;

public class LockedCounter implements Counter {

    private long data;

    public LockedCounter(boolean fair) {
        this.data = 0;
        // TODO: implement me
    }

    @Override
    public void increment() {
        // TODO: implement me
    }

    @Override
    public long get() {
        // TODO: implement me
        return 0;
    }
}
