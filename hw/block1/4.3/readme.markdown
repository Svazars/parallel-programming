# Task 4.3 (CountDownLatch)

## Description

### Easy

Create multithreaded stress test that does the following:
- `N` threads arrive to `CountDownLatch(N + 1)`
- After that, `Arbiter` thread records `System.nanoTime()` and invokes `countDown`
- Every released thread records `System.nanoTime()` as well
- Test prints difference in time between latest released thread and arbiter timestamp

Analyze ''release latency'' for `NaiveCountDownLatch` (Lecture 4) using varying `N`.
Do not forget to run many test runs and use averaging (your plots should contain error-bars).
Explain the results.

### Medium

Implement `MonitorCountDownLatch` that uses the following algorithm:
- Every thread that invoked `await` on closed latch creates instance of special class `Token`, adds this instance to thread-safe queue of pending threads and `wait`s on `Token`.
- Thread that invoked final `countDown` (`counter` dropped to zero) iterates through all pending threads and invokes `notify` on corresponding `Token`s.

Analyze ''release latency'' for `MonitorCountDownLatch` using varying `N`.
Plot the same delay graph, explain the results. 

## Requirements

Your solution to this task should contain at least two parts:
- Source code for your experiments
- Answers to the questions in `pdf` file. Feel free to use any typesetting system (.tex + pdflatex, .markdown + pandoc, LibreOfficeWriter + Export as pdf ...).

Please note:
- Latency discussion should be supported with numbers (e.g. you should plot max delay on y-axis with varying number of threads on x-axis).
- Do not forget to add error bars to your plots.
- Consider using some automation for graph drawing process. We tend to frequently ask students to tweak few lines of code and redraw the performance figures.
