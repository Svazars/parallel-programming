package org.nsu.syspro.parprog.helpers;

import org.nsu.syspro.parprog.base.DiningTable;
import org.nsu.syspro.parprog.interfaces.Fork;
import org.nsu.syspro.parprog.interfaces.Philosopher;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class TestLevels {

    enum Level {
        EASY, MEDIUM, HARD
    }

    private static final Level CURRENT_LEVEL = Level.HARD;

    public static boolean easyEnabled() {
        return CURRENT_LEVEL.ordinal() >= Level.EASY.ordinal();
    }

    public static boolean mediumEnabled() {
        return CURRENT_LEVEL.ordinal() >= Level.MEDIUM.ordinal();
    }

    public static boolean hardEnabled() {
        return CURRENT_LEVEL.ordinal() >= Level.HARD.ordinal();
    }

    public static <P extends Philosopher, F extends Fork, T extends DiningTable<P, F>> T dine(T table, int seconds) {
        table.start();
        sleepSeconds(seconds);
        table.stop();
        return table;
    }

    public static void sleepMillis(long s) {
        try {
            Thread.sleep(ofMillis(s).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleepSeconds(int s) {
        try {
            Thread.sleep(ofSeconds(s).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
