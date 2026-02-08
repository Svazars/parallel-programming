# Task 1.3

## Description

Consider the following multithreaded program
```java
static int x = 0;
static int y = 0;
static int z = 0;

class A extends Thread {
  public void run() {
    int a_x = x;   // A.1
    int a_z = z;   // A.2
    y = a_x + a_z; // A.3    
  }
}

class B extends Thread {
  public void run() {
    int b_x = x; // B.1
    x = b_x + 1; // B.2
    int b_z = z; // B.3
    z = b_z + 1; // B.4    
  }
}

class C extends Thread {
  public void run() {
    int c_y = y;    // C.1
    if (c_y == 2) { // C.2
      int c_x = x;  // C.3
      x = c_x - 1;  // C.4
    }
  }
}
```

Assume `main` thread `join`ed threads `A`, `B` and `C`. Use interleaving model for formalization of possible and impossible execution traces. 
- Provide all possible execution **results** in the format `(x=XXX, y=YYY, z=ZZZ) + execution_trace` using `A.1->A.2->B.1 ...` notation for execution traces. **Note:** there could be multiple execution traces that lead to the same execution result. Provide only one execution trace per result, no need to explicitly list all of them.
- Prove exhaustiveness of your answer. Why are you sure that all other (`x=XXX, y=YYY, z=ZZZ`) tuples are impossible to reach?

**Hint.** Proof of exhaustiveness could be structured in the following way:
- Phase 1.
  -- Prove that any tuple with `x=XXX where XXX > 100` is unreachable. Use the same technique for proof of impossibility as you used in task 1.2.
  -- Prove that any tuple with `x=XXX where XXX < -100` is unreachable.
  -- Do the same trick with `YYY` and `ZZZ` bounds.
- Phase 2. Now you have a finite amount of "tuples with unknown status". 
  -- Analyze them one-by-one and finish the proof of exhaustiveness.

**Hint inside hint**: better you choose the "impossibility" limits on phase 1, less tuples you have to analyze manually in phase 2.

## Requirements

Ensure your answer is clear, readable and concise.
