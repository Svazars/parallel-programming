package org.nsu.syspro.parprog.interfaces;

public interface Fork {
    long id();

    void acquire();

    void release();
}
