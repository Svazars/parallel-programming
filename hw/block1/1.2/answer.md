### #1
**Answer:** `yes`

`A.1->A.2->A.3->A.4->B.1->B.2->B.3->B.4->B.5->A.5`

`A.1->A.2->A.3->A.4:` `y = 0, x = 0`

`B.1->B.2->B.3->B.4->B.5:` `x = z + 1 = 0 + 1 = 1, r_z = z = 0`

`A.5:` `x = y + 1 = 0 + 1 = 1, r_y = y = 0`



### #2
**Answer:** `yes`

`A.1->A.2->A.3->A.4->B.1->B.2->B.3->A.5->B.4->B.5`

`A.1->A.2->A.3->A.4:` `y = 0, x = 0`

`B.1->B.2->B.3:` fall into `if` because x = 0 yet

`A.5:` `x = 1, r_y = y = 0`

`B->4->B.5:` `z = x = 1, x = z + 1 = 2, r_z = z = 1`

### #3
**Answer:** `no`


- assume there exists concurrent execution trace where `main` thread observes `x == 1, r_y == 0, r_z == 1`
- to observe `r_z == 1`, event `B.4` must be executed when `x == 1`
- therefore `A.5` with `y == 0` happened earlier than `B.4`
- to observe the final state `x == 1`, event `B.5` (which sets `x` to `2` because `z == 1`) happened earlier than other `x` assignment, which is `A.5`
- we have contradiction: `A.5` earlier than `B.4` which earlier than `B.5` which earlier than `A.5`. So, `A.5` earlier than `A.5`
- therefore, it is impossible to observe `x == 1, r_y == 0, r_z == 1` when using interleaving model of execution