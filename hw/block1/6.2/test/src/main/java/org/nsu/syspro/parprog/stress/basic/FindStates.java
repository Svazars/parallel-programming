package org.nsu.syspro.parprog.stress.basic;

import org.openjdk.jcstress.annotations.*;

@JCStressTest
@State
public class FindStates {
    int x, y, z;

    @Actor 
    public void a() {
        // TODO: task 1.3    
    }
    
    @Actor 
    public void b() {
        // TODO: task 1.3
    }
    
    @Actor 
    public void c() {
        // TODO: task 1.3
    }

    @Arbiter
    public void main(III_Result r) {
        r.r1 = x;
        r.r2 = y;
        r.r3 = z;
    }
}
