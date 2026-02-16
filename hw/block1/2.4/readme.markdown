# Task 2.4

## Description

### How to start development

Command line:
```bash
mvn clean verify
java -jar target/benchmarks.jar
```

IntelliJ IDEA: open .pom as project, use Java 11 for [project source level](https://www.jetbrains.com/help/idea/project-settings-and-structure.html#language-level) 
and [target bytecode level](https://www.jetbrains.com/help/idea/java-compiler.html).

### Easy

Implement `ThreadUnsafeCounter` (`hw/block1/2.4/test/src/main/java/org/nsu/syspro/parprog/counters/impls/ThreadUnsafeCounter.java`). 
Use [Java Microbenchmark Harness (JMH)](https://github.com/openjdk/jmh) to collect single-threaded and multi-threaded performance 
of your code in `get`-only and `increment`-only scenarios. Use `CountersBenchmark` as a skeleton of your work, do not 
forget to add benchmarks for `get` operation.

Please note that `NoContentionBaseline` runs `N` threads where each thread increments **it's own** copy of counter. 
As you can see, more CPUs involved == better throughput reported by benchmarking system.

`CountersBenchmark` runs `N` threads that increment **the same** shared counter instance. Answer the following questions:
- Are you sure that compiler did not optimize out your workload function? Consider reading [JMH DCE hints](https://github.com/openjdk/jmh/blob/master/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_08_DeadCode.java)
and ["The Lesser of Two Evils" Story](https://shipilev.net/#benchmarking).
- Does this implementation scales well? Provide experimental data: how throughput changes when more CPUs (threads) are added. 
- Does this implementation works well in case of over-subscription? Provide experimental data: how system behaves when 
there are more working threads than available CPUs.
- Which operation seems to scale better -- `increment` or `get`?

Implement `LockedCounter` using `ReentrantLock`. Use JMH to collect single-threaded and multi-threaded performance
of your code in `get`-only and `increment`-only scenarios. Analyse both `ReentrantLock(fair=true)` and `ReentrantLock(fair=false)`
cases. Answer the following questions for each case:
- Does this implementation scales well?
- Does this implementation works well in case of over-subscription?
Do not forget to provide experimental data.

Provide your opinions on the following questions:
- Is there a performance gap between single-threaded execution of thread-unsafe and thread-safe implementations? 
Does it depend on type of operation (`increment`/`get`)? 
- Is there a performance gap between `fair=true` and `fair=false` implementations? Could you explain this in terms 
of OS scheduler overheads and context switch cost?

### Medium

Implement thread-safe `SplitCounter` using `lock splitting` pattern from lecture 2. Note that constructor of 
`SplitCounter` expects locking granularity (number of `ReentrantLock`s to be used for splitting). 
Algorithm should work in the following way:
- `increment` operation should lock only one of split mutexes (consider using `Thread.currentThread().getId() % GRANULARTITY` or similar to select the victim) 
and increment corresponding "portion" of counter. Consider using `ReentrantLock` array of the `GRANULARITY` size.
- `get` operation should lock all mutexes, sum all "portions" to get the actual value of split counter and then unlock all mutexes.

Answer the following questions:
- Does your implementation works consistently in concurrent environment? Besides the explanation provide at least 5 
correctness tests, at least 3 of them should be stress-tests.
- Does this implementation scales well?
- Does this implementation works well in case of over-subscription?
- Which operation seems to scale better -- `increment` or `get`? Could you explain this in terms of thread contention?
- Could you artificially force different threads to use the same "portion" and therefore contend for the same mutex? 
Which single-threaded data structure has the same problem with collisions? How to alleviate this?

### Hard

Note: this task is HARD because you are not (yet) familiar with formal definitions of concurrent consistency and concurrent 
specification that would appear in lecture 7. However, these particular code snippets could be solved using "Concurrent consistency" 
slide from lecture 2 and a fair amount of common sense. Do not hesitate to provide draft of your thoughts to lecturer/assistants.

#### BulkSplitCounter

Consider the following algorithm for `BulkSplitCounter` that allows arbitrary positive `increment`s:
```java
final long[] portion = ...;
final ReentrantLock[] locks = ...;

private int idx() {
    return (int) (Thread.currentThread().getId() % GRANULARITY);
}

public void increment(long delta) {
	if (delta <= 0) {
		throw IllegalArgumentException();
	}
	for (long i = 0; i < delta; i++) {
		final var l = locks[idx()];
		l.lock();
		try {
			portion[idx()]++;
		} finally {
			l.unlock();
		}
	}
}
public void get() {
	long result = 0;
	for (int i = 0; i < GRANULARITY; i++) locks[i].lock();
	for (int i = 0; i < GRANULARITY; i++) result += portion[i];
	for (int i = 0; i < GRANULARITY; i++) locks[i].unlock();
	return result;
}

```
Answer the following questions:
- Is it true that `increment` does not miss any increments? (Safety)
- Is it true that `increment`/`get` never deadlock? (Progress)
- Is it true that this counter works "logically"? In other words, is it possible that in multi-threaded environment 
threads will `get` values that "never existed" (sometimes it is called "out-of-thin-air" values)? (Consistency).
For example, if the whole multi-threaded program has the only one `increment` operation (`x.increment(5)`), is it possible 
  - a) that some of `x.get()` invocations would return `0`? 
  - b) that some of `x.get()` invocations would return `5`? 
  - c) that some of `x.get()` invocations would return `-1`?
  - d) that some of `x.get()` invocations would return `3`?  

For every `a-d` case provide either reproducing example (source code + execution trace) or explanation why such outcome is impossible.

#### NegativeSplitCounter

Consider the following implementation of `NegativeSplitCounter` that allows negative `increment`s:
```java
final long[] portion = ...;
final ReentrantLock[] lock = ...;

private int idx() {
	return Thread.currentThread().getId() % GRANULARTITY;
}

public void increment(long delta) {
	final var l = lock[idx()];
	l.lock();
	try {
		portion[idx()] += delta;
	} finally {
		l.unlock();
	}
}

public void get() {
	long result = 0;
	for (int i = 0; i < GRANULARITY; i++) {
		final var l = lock[idx()];
		l.lock();
		try {
			result += portion[idx()];
		} finally {
			l.unlock();
		}
	}
	return result;
}
```
Answer the following questions:
- Is it true that `increment` does not miss any increments?
- Is it true that `increment`/`get` never deadlock?
- It seems that `get` method holds at most one `ReentrantLock` at a time. Is it good or bad for scalability 
(compared to "lock everything" approach used in `BulkSplitCounter.get`)? 
- Are those implementations of `increment` and `get` correct in multi-threaded environment? Is it possible that in 
multi-threaded environment threads will `get` values that "never existed"?

## Requirements

Your solution to this task should contain at least two parts:
- Source code for your experiments (fork the repo and prepare a branch for review)
- Answers to the questions in `pdf` file. Feel free to use any typesetting system (.tex + pdflatex, .markdown + pandoc, LibreOfficeWriter + Export as pdf ...).

Please note:
- Performance discussion should be supported with numbers (e.g. for scalability you should plot throughput on y-axis with varying number of CPUs on x-axis)
- Do not forget to add error bars to your plots
- Consider using some automation for graph drawing process. We tend to frequently ask students to tweak few lines of code and redraw the performance figures.
