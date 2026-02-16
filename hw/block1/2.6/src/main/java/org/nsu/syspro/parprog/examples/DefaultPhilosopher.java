package org.nsu.syspro.parprog.examples;

import org.nsu.syspro.parprog.interfaces.Fork;
import org.nsu.syspro.parprog.interfaces.Philosopher;

import java.util.concurrent.atomic.AtomicLong;

public class DefaultPhilosopher implements Philosopher {

    private static final AtomicLong idProvider = new AtomicLong(0);
    public final long id;
    private long successfulMeals;

    public DefaultPhilosopher() {
        this.id = idProvider.getAndAdd(1);
        this.successfulMeals = 0;
    }

    @Override
    public long meals() {
        return successfulMeals;
    }

    @Override
    public void countMeal() {
        successfulMeals++;
    }

    public void onHungry(Fork left, Fork right) {
        // TODO: implement me properly
        eat(left, right);
    }

    @Override
    public String toString() {
        return "DefaultPhilosopher{" +
                "id=" + id +
                '}';
    }
}
