package org.nsu.syspro.parprog.stress.basic;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE;
import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE_INTERESTING;

@JCStressTest
// @Outcome(id = "1, 1", expect = ACCEPTABLE_INTERESTING, desc = "Data race")
@Outcome(id = "1, 2", expect = ACCEPTABLE, desc = "actor1 -> actor2.")
@Outcome(id = "2, 1", expect = ACCEPTABLE, desc = "actor2 -> actor1.")
@State
public class TestConcurrentIncrements {
    int v;
    @Actor public void actor1(II_Result r) {
        r.r1 = ++v;
    }
    @Actor public void actor2(II_Result r) {
        r.r2 = ++v;
    }
}
