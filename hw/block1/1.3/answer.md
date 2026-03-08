### Possible Execution Results

* **(x=0, y=2, z=1)** `B.1->B.2->B.3->B.4->A.1->A.2->A.3->C.1->C.2->C.3->C.4`
* **(x=1, y=0, z=1)** `A.1->A.2->A.3->B.1->B.2->B.3->B.4->C.1->C.2`
* **(x=1, y=1, z=1)** `B.1->B.2->A.1->A.2->A.3->B.3->B.4->C.1->C.2`
* **(x=1, y=2, z=1)** `C.1->C.2->B.1->B.2->B.3->B.4->A.1->A.2->A.3`

### Proof of Exhaustiveness

#### Phase 1: Establishing Variable Bounds

Since the `main` thread joins `A`, `B`, and `C`, we only consider states where all three threads have fully completed their execution.

* **Bounds for `z`:**
* Variable `z` is initially `0`.
* Only thread `B` modifies `z` (at `B.4` where `z = b_z + 1`).
* Since thread `B` has no branching and always runs to completion, the only write to `z` is `z = 0 + 1 = 1`.
* Therefore, any tuple with **`z != 1`** is unreachable.


* **Bounds for `x`:**
* Variable `x` is initially `0`.
* It is incremented once unconditionally by thread `B` (`B.2`).
* It may be decremented once conditionally by thread `C` (`C.4`).
* The maximum possible value is `1` (if `B` increments and `C` does not decrement). The minimum possible value is `-1` (if `C` reads `0` and decrements before `B` increments).
* Therefore, any tuple with **`x > 1`** or **`x < -1`** is unreachable. Valid set: `x ∈ {-1, 0, 1}`.


* **Bounds for `y`:**
* Variable `y` is initially `0`.
* It is modified only once by thread `A` (`A.3`), where `y = a_x + a_z`.
* Based on the previously established bounds, at the moment of reading, the minimum values are `a_x = -1` and `a_z = 0` (resulting in `y = -1`).
* The maximum values are `a_x = 1` and `a_z = 1` (resulting in `y = 2`).
* Therefore, any tuple with **`y > 2`** or **`y < -1`** is unreachable. Valid set: `y ∈ {-1, 0, 1, 2}`.



#### Phase 2: Analyzing Tuples with Unknown Status

By combining the bounds from Phase 1, we have exactly 12 mathematically possible combinations (3 variants for `x` multiplied by 4 variants for `y`, and 1 variant for `z`).
We will now prove that the 8 tuples not listed in the "Possible Execution Results" section are logically unreachable.

| x | y | z | 
| :---: | :---: | :---: |
|-1|-1 | 1 |
|-1|0 | 1 |
|-1|1 | 1 |
|-1|2 | 1 |
|0|-1 | 1 |
|0|0 | 1 |
|0|1 | 1 |
|0|2 | 1 |
|1|-1 | 1 |
|1|0 | 1 |
|1|1 | 1 |
|1|2|1|

**1. Proof of impossibility for all tuples where `x == -1`:**
*(Covers tuples: (-1, -1, 1), (-1, 0, 1), (-1, 1, 1), (-1, 2, 1))*

* assume there exists a concurrent execution trace where `main` thread observes final `x == -1`
* then `C.4` (which writes `-1`) must be executed
* to write `-1` at `C.4`, `C.3` must read `c_x == 0`
* then `C.3` happened earlier than `B.2` (which writes `x = 1`)
* to reach `C.3`, the condition at `C.2` must be true, meaning `C.1` read `y == 2`
* then `A.3` (which writes `y = 2`) happened earlier than `C.1`
* to write `y == 2` at `A.3`, `A.1` must read `a_x == 1` and `A.2` must read `a_z == 1`
* then `B.2` (which writes `x = 1`) happened earlier than `A.1`
* we have a contradiction: `C.3` happened earlier than `B.2` which happened earlier than `A.1` which happened earlier than `A.3` which happened earlier than `C.1` which happened earlier than `C.3`. So, `C.3` happened earlier than `C.3`.
* therefore, it is impossible to observe `x == -1` when using the interleaving model of execution.

**2. Proof of impossibility for all tuples where `x == 0` and `y != 2`:**
*(Covers tuples: (0, -1, 1), (0, 0, 1), (0, 1, 1))*

* assume there exists a concurrent execution trace where `main` thread observes final `x == 0` and `y != 2`
* to observe final `x == 0`, `C.4` must be executed to decrement the `x == 1` written by `B.2`
* to execute `C.4`, the condition at `C.2` must be true, meaning `C.1` read `y == 2`
* then `A.3` (which is the only assignment to `y`) must have written `y = 2`
* then `A.3` happened earlier than `C.1`
* since `A.3` is the absolute only assignment to `y` in the entire program, the final value of `y` is permanently determined by it
* we have a contradiction: the only possible final value for `y` is `2` vs. the assumption that the final value of `y != 2`
* therefore, it is impossible to observe `x == 0` with `y != 2` when using the interleaving model of execution.

**3. Proof of impossibility for tuple `(x=1, y=-1, z=1)`:**
*(Covers the last remaining tuple: (1, -1, 1))*

* assume there exists a concurrent execution trace where `main` thread observes final `y == -1`
* then `A.3` must write `y = -1`
* to write `y = -1`, `A.1` must read `a_x == -1`
* then `C.4` (the only instruction that can set `x` to `-1`) happened earlier than `A.1`
* to execute `C.4`, `C.1` must read `y == 2`
* then `A.3` (which writes `y = 2`) happened earlier than `C.1`
* we have a contradiction: `A.3` happened earlier than `C.1` which happened earlier than `C.4` which happened earlier than `A.1` which happened earlier than `A.3`. So, `A.3` happened earlier than `A.3`.
* therefore, it is impossible to observe `x=1, y=-1, z=1` when using the interleaving model of execution.