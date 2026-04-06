# Task 7.1 (Precedence)

## Description

Assume we have a (possibly infinite) set of events `x1, x2, x3 ...`. Those events are totally ordered, i.e. for every two different events `x` and `y` (`x != y`) either `x -> y` or `y -> x`. We define an interval `X` as a pair of two events `(x1, x2)` where `x1 -> x2`.

**Side note**: usually partial orders defined via binary relation denoted as `<` but here we use symbol `->`.

Two different intervals `X = (x1, x2)` and `Y = (y1, y2)` are **disjoint** iff `x2 -> y1` or `y2 -> x1`.
If `X` and `Y` are not disjoint then we say they are **overlapping** intervals.

If `X` and `Y` are disjoint and `x2 -> y1` then we say that `X` **precede** `Y` writing `X -> Y`.

### Task 7.1.1

Prove that for two arbitrary intervals `X` and `Y` at least one of the following cases holds:
- `X` and `Y` are disjoint and `X -> Y`; or
- `X` and `Y` are disjoint and `Y -> X`; or
- `X` and `Y` are overlapping intervals

### Task 7.1.2

Prove that `->` (precedence) binary relation on intervals is a partial order that:
- Irreflexive. Never true that `X -> X`.
- Antisymmetric. If `X -> Y` then not true `Y -> X`.
- Transitive. If `X -> Y` and `Y -> Z` then `X -> Z`.

Please also prove that precedence is indeed a partial order: provide an example where `X -> Y` false and `Y -> X` is also false.

## Requirements

Ensure your answer is clear, readable and concise.
