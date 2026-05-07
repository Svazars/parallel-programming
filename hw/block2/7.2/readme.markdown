# Task 7.2 (Filter lock)

## Description

Consider Filter Lock algorithm from lecture 7.

- Prove that Filter Lock algorithm satisfies mutual exclusion. (Corollary 2.4.1 from ''The Art of Multiprocessor Programming'').
- Prove that Filter Lock algorithm is starvation-free. (Lemma 2.4.2 from ''The Art of Multiprocessor Programming'').

Consider reading ''The Art of Multiprocessor Programming'' pages 28-31 for proof outline, pages 21-24 for formal definitions of mutual exclusion and freedom from starvation. 

## Requirements

Ensure your answer is clear, readable and concise. Solution is discussed face-to-face on practical lesson, no need to send e-mail.

``` java
class Filter implements Lock {
	int[] level;
	int[] victim;
	public Filter(int n) {
		level = new int[n];
		victim = new int[n]; // use 1..n-1
		for (int i = 0; i < n; i++) {
			level[i] = 0;
		}
	}
	public void lock() {
		int me = ThreadID.get();
		for (int i = 1; i < n; i++) { // attempt level i
			level[me] = i;
			victim[i] = me;
			// spin while conflicts exist
			while ((∃k != me) (level[k] >= i && victim[i] == me)) {};
		}
	}
	public void unlock() {
		int me = ThreadID.get();
		level[me] = 0;
	}
}
```

- Prove that Filter Lock algorithm satisfies mutual exclusion.
- Prove that Filter Lock algorithm is starvation-free.

starvation (lockout)-freedom: Every thread that attempts to acquire the lock eventually succeeds. Every call to lock() eventually returns

## Mutual Exclusion

Assume for contradiction that two threads $i \neq j$ are both in the critical section.
To enter, both must pass level n-1, so:
- $level[i] \ge n-1$
- $level[j] \ge n-1$
At level $n-1$, $victim[n-1] \in {i,j}$.
- If $victim[n-1] = i$, then $i$ waits while  
    $(\exists k \ne i)(level[k] \ge n-1 \land victim[n-1] = i)$.  
    Since $k = j$ satisfies this, $i$ cannot pass.
- If $victim[n-1] = j$, symmetric argument blocks $j$.
Contradiction.  
At most one thread enters the critical section.

Starvation Freedom
Fix a thread $T$.
At level i, T waits only if
$(\exists k \ne T)(level[k] \ge i \land victim[i] = T)$.
but
Only one thread can be the victim at level $i$.
Threads ahead of $T$ eventually either:
advance to higher levels, or
exit and set level = 0.
Thus, the set ${k \ne T \mid level[k] \ge i}$ eventually empties or victim\[i] $\ne$ T.
So the waiting condition becomes false, and T advances.
By induction over i=1, ..., n-1, T reaches the critical section. => No thread starves.