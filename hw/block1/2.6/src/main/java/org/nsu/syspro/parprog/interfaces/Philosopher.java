package org.nsu.syspro.parprog.interfaces;

public interface Philosopher {
    long meals();
    void countMeal();

    default void eat(Fork f1, Fork f2) {
        f1.acquire();
        try {
            f2.acquire();
            try {
                countMeal();
            } finally {
                f2.release();
            }
        } finally {
            f1.release();
        }
    }

    void onHungry(Fork left, Fork right);
}
