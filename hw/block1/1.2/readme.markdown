# Task 1.2

## Description

Consider the following multithreaded program
```java
static int x = 0;

static int r_y = 0;
static int r_z = 0;

class A extends Thread {
  public void run() {
    int y = -1;       // A.1
    int a_x = x;      // A.2
    if (a_x == 0) {   // A.3
      y = x;          // A.4
      x = y + 1;      // A.5
    }
    r_y = y;
  }
}

class B extends Thread {
  public void run() {
    int z = -1;       // B.1
    int b_x = x;      // B.2
    if (b_x == 0) {   // B.3
      z = x;          // B.4
      x = z + 1;      // B.5
    }
    r_z = z;
  }
}
```

Assume `main` thread `join`ed both thread `A` and thread `B`. Use interleaving model for formalization of possible and impossible execution traces. For every question below provide either concurrent execution trace (in the form `A.1->A.2->B.1 ...`) or proof of impossibility:

- Could `main` thread observe `x == 1, r_y == 0, r_z == 0`?
- Could `main` thread observe `x == 2, r_y == 0, r_z == 1`?
- Could `main` thread observe `x == 1, r_y == 0, r_z == 1`?

**Hint.** Proof of impossibility could be structured in the following way:
- assume there exists concurrent execution trace where `main` thread observes `var == N`
- then `event_a` happened earlier than `event_b`
- then `event_b` happened earlier than `event_c`
- but also, according to program order, `event_c` always happens earlier than `event_a`
- we have a contradiction: `event_a` happened earlier than `event_c` vs. `event_c` happened earlier than `event_a`
- therefore, it is impossible to observe `var == N` when using interleaving model of execution

## Requirements

Do not answer "yes" or "no", provide a trace with explanation or properly structured proof.
