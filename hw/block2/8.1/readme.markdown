# Task 8.1 (Obstruction-free example)

## Prerequisite

- [Task 7.1](https://github.com/Svazars/parallel-programming/tree/main/hw/block2/7.1)

## Description

Assume there are only two threads: thread A (`id == 0`) and thread B (`id == 1`). Consider the following pseudo-code
```java

static boolean flags = new boolean[2]; // initially zero

public void foo() {
    int i = ThreadId.get();                                      // F.1
    int j = 1 - i;                                               // F.2
    while (true) {                                               // F.3
        flags[i] = true;             // i would like to enter    // F.4
        if (flags[j] == false) {     // you don't                // F.5
            if (flags[i] == true) {  // my request was not reset // F.6
                break;               // i win                    // F.7
            }
        } else {
            // looks like we have a contention
            flags[i] = false; // retreat                         // F.8
            flags[j] = false; // forcibly reset competitor       // F.9
        }
    }
}
```

Answer the following questions related to method `foo`:
- Is it wait-free? Prove or provide a counter-example.
- Is it lock-free? Prove or provide a counter-example.
- Is it obstruction-free? In order to prove such property
  - assume Thread `i` reached `F.X` and then thread `j` was frozen
  - ensure Thread `i` is able to reach `F.7`
  **Hint**: do not forget that memory state (`flag[0]` and `flag[1]`) could be different, you should investigate quite a lot of cases.
- Does it guarantee starvation-freedom?
- Does it guarantee deadlock-freedom? Reminder: cycle in wait-for graph could appear because of busy waiting.

## Requirements

Ensure your answer is clear, readable and concise.
