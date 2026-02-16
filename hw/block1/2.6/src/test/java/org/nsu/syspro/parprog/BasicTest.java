package org.nsu.syspro.parprog;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.nsu.syspro.parprog.base.DefaultFork;
import org.nsu.syspro.parprog.base.DiningTable;
import org.nsu.syspro.parprog.examples.DefaultPhilosopher;
import org.nsu.syspro.parprog.helpers.TestLevels;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicTest extends TestLevels {

    static final class BasicTable extends DiningTable<DefaultPhilosopher, DefaultFork> {
        public BasicTable(int N) {
            super(N);
        }

        @Override
        public DefaultFork createFork() {
            return new DefaultFork();
        }

        @Override
        public DefaultPhilosopher createPhilosopher() {
            return new DefaultPhilosopher();
        }
    }

    @EnabledIf("easyEnabled")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    @Timeout(2)
    void testProgress(int N) {
        final BasicTable table = dine(new BasicTable(N), 1);
        assertTrue(table.maxMeals() > 0); // at least one philosopher eat once
    }

    @EnabledIf("mediumEnabled")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    @Timeout(8)
    void testScalability(int N) {
        final BasicTable table1 = dine(new BasicTable(N), 1);
        final BasicTable table2 = dine(new BasicTable(N), 4);
        assertTrue(2 * table1.maxMeals() < table2.maxMeals()); // more time provided -- more food consumed
    }

    @EnabledIf("mediumEnabled")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    @Timeout(2)
    void testWeakFairness(int N) {
        final BasicTable table = dine(new BasicTable(N), 1);
        assertTrue(table.minMeals() > 0); // every philosopher eat at least once
    }

    @EnabledIf("hardEnabled")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    @Timeout(2)
    void testStrongFairness(int N) {
        final BasicTable table = dine(new BasicTable(N), 1);
        final long minMeals = table.minMeals();
        final long maxMeals = table.maxMeals();
        assertTrue(maxMeals < 1.5 * minMeals); // some king of gini index for philosophers
    }
}