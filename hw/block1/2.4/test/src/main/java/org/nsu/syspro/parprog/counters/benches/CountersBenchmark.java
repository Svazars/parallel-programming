package org.nsu.syspro.parprog.counters.benches;

import org.nsu.syspro.parprog.counters.impls.Counter;
import org.nsu.syspro.parprog.counters.impls.LockedCounter;
import org.nsu.syspro.parprog.counters.impls.SplitCounter;
import org.nsu.syspro.parprog.counters.impls.ThreadUnsafeCounter;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class CountersBenchmark {

    // region utility
    enum CounterType {
        Unsafe,
        Unfair,
        Fair,
        Split_1,
        Split_2,
        Split_10000;
        // TODO: add more

        Counter createCounter() {
            switch (this) {
                case Unsafe:
                    return new ThreadUnsafeCounter();
                case Unfair:
                    return new LockedCounter(false);
                case Fair:
                    return new LockedCounter(true);
                case Split_1:
                    return new SplitCounter(1);
                case Split_2:
                    return new SplitCounter(2);
                case Split_10000:
                    return new SplitCounter(10000);
                default:
                    throw new IllegalArgumentException("Unexpected counter type: " + this);
            }
        }
    }

    // Feel free to comment/uncomment to speed up the measurements
    @Param({
            "Unsafe",
            "Unfair",
            "Fair",
            "Split_1",
            "Split_2",
            "Split_10000",
    })
    String counterType;
    Counter counterInstance;

    @Setup
    public void setup() {
        counterInstance = CounterType.valueOf(counterType).createCounter();
    }

    // endregion

    // region increment
    @Benchmark
    @Threads(1)
    public void inc1() {
        counterInstance.increment();
    }

    @Benchmark
    @Threads(2)
    public void inc2() {
        counterInstance.increment();
    }

    @Benchmark
    @Threads(4)
    public void inc4() {
        counterInstance.increment();
    }

    @Benchmark
    @Threads(Threads.MAX)
    public void incMax() {
        counterInstance.increment();
    }

    @Benchmark
    @Threads(24) // should 2 * Threads.MAX
    public void incMaxMax() {
        counterInstance.increment();
    }
    //endregion

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CountersBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
