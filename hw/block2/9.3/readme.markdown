# Task 9.3

## Description

Implement `TASLock`, `TATASLock` and `ExpBackoffLock` from Lecture 9. Use [Java Microbenchmark Harness (JMH)](https://github.com/openjdk/jmh) to collect multi-threaded performance of your code.

- Study throughput for 1, 2, 3, 4, 5, 6, 7, 8, 16, 32 simultaneously running threads.
- Scenario №1: small critical section for common memory (increment shared static long counter inside critical section)
- Scenario №2: small critical section for independent memory (increment thread-local long counter inside critical section)
- Scenario №3: CPU-intensive critical section (compute `n`-th [Fibonacci number](https://en.wikipedia.org/wiki/Fibonacci_sequence) inside critical section). Vary `n` and study behaviour of concurrent system. **Note**: use naive recursive algorithm for computing `n`-th Fibonacci number.

Answer the following questions:
- Which lock is the best for uncontended case?
- Which approach scales better?
- Is there a performance gap between single-threaded and multi-threaded execution? Could you explain this?

## Requirements

Your solution to this task should contain at least two parts:
- Source code for your experiments (fork the repo and prepare a branch for review)
- Answers to the questions in `pdf` file. Feel free to use any typesetting system (.tex + pdflatex, .markdown + pandoc, LibreOfficeWriter + Export as pdf ...).

Please note:
- Performance discussion should be supported with numbers (e.g. for scalability you should plot throughput on y-axis with varying number of CPUs on x-axis)
- Do not forget to add error bars to your plots
- Consider using some automation for graph drawing process. We tend to frequently ask students to tweak few lines of code and redraw the performance figures.
