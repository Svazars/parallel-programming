# Overview

Programming assignment for [lecture 2](https://github.com/Svazars/parallel-programming/blob/main/slides/pdf/l2.pdf),
https://en.wikipedia.org/wiki/Dining_philosophers_problem.

## Getting started

- Prepare IDE environment (InteliJ IDEA "open pom.xml as project" or equivalent).
- Run all unit tests (it is OK if they fail).
- Read unit tests. Pay attention to CustomSchedulingTest which "wraps" class-under-test (DefaultPhilosopher)
  with custom scheduling policy ("preselected sleeps") to simplify concurrency bugs detection.

## Programming assignment

Implement `DefaultPhilosopher.onHungry` method to avoid deadlock situation. You are allowed to define auxiliary classes 
and data structures, define and initialize additional static variables. However, you should not modify source code of any 
already existing class except `DefaultPhilosopher.onHungry` method. You also should not "hack" source code, e.g. you are 
not allowed to override `DefaultFork` behaviour or replace `Philosopher.eat` implementation.

Feel free to add `println`/`sleep` anywhere so you better understand concurrent behaviour of the code.

### Acceptance criteria

#### Level 1 (easy)

- `BasicTest.testProgress` pass
- `CustomSchedulingTest.testDeadlockFreedom` pass
- You implemented new unit test `CustomSchedulingTest.testSingleSlow` with custom scheduling:
    - philosopher number 1 grabs both forks and sleeps for one second
    - other philosophers eat very fast (no additional delay)
    - at the very end test system check that at least one philosopher has eaten >= 1000 times
- Your custom test passes

#### Level 2 (medium)

- Level 1 pass
- `BasicTest.testScalability` pass
- `BasicTest.testWeakFairness` pass
- `BasicTest.testWeakFairness` is rewritten as `CustomSchedulingTest.testWeakFairness` using the following rule:
    - even-numbered philosophers eat 10x times faster than odd-numbered philosophers

#### Level 3 (hard)

- Level 2 pass
- `BasicTest.testStrongFairness` pass
- `BasicTest.testStrongFairness` is rewritten as `CustomSchedulingTest.testImpossibleFairness` using the following rule:
    - some philosophers are REALLY faster than the others
    - every philosopher has a chance to eat (you cannot use `sleep 1 hour` hack)
    - this test will fail (`(maxMeals < 1.5 * minMeals) == false`) on **any** implementation
