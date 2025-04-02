package org.nsu.syspro.parprog.helpers;

import org.nsu.syspro.parprog.UserThread;
import org.nsu.syspro.parprog.examples.AdaptiveCompiler;
import org.nsu.syspro.parprog.examples.CachingTopTierJIT;
import org.nsu.syspro.parprog.examples.Interpreter;
import org.nsu.syspro.parprog.external.CompilationEngine;
import org.nsu.syspro.parprog.external.ExecutionEngine;
import org.nsu.syspro.parprog.solution.SolutionThread;

import java.time.Duration;

public abstract class TestLevels {

    public abstract TestEnvironment testEnvironment();

    public static int compilationThreadBound() {
        // must be >= 2
        return 3;
    }

    public static UserThread createUserThread(ExecutionEngine e, CompilationEngine c, Runnable r) {
        // return new Interpreter(e, c, r);
        // return new AdaptiveCompiler(e, c, r);
        // return new CachingTopTierJIT(e, c, r);
        return new SolutionThread(compilationThreadBound(), e, c, r);
    }

    enum Level {
        EASY, MEDIUM, HARD
    }

    private static final Level CURRENT_LEVEL = Level.MEDIUM;

    public static boolean easyEnabled() {
        return CURRENT_LEVEL.ordinal() >= Level.EASY.ordinal();
    }

    public static boolean mediumEnabled() {
        return CURRENT_LEVEL.ordinal() >= Level.MEDIUM.ordinal();
    }

    public static boolean hardEnabled() {
        return CURRENT_LEVEL.ordinal() >= Level.HARD.ordinal();
    }

    public static void sleepNanos(long nanos) {
        if (nanos == 0) {
             return;
        }

        final long millis = Duration.ofNanos(nanos).toMillis();
        final int leftoverNanos = (int) (nanos - Duration.ofMillis(millis).toNanos());
        try {
            Thread.sleep(millis, leftoverNanos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleepSeconds(int seconds) {
        sleepNanos(Duration.ofSeconds(seconds).toNanos());
    }
}
