# Task 6.3 (Introduction to JPF)

## Description

- Switch to `parallel-programming/hw/block1/6.3/jpf-sample`
- Run `test.sh`. It should print all possible execution results to the stdout. Some would be printed several times, it is OK.
- Change `Task12.jpf`, define `target.args=1`. Now every execution result is accompanied with execution trace. Please note that logging itself is a synchronized operation with side-effects (e.g. heap-mutating) therefore: 
  - There are more interleavings to explore, we have more "duplicates" among results
  - Analysis is taking more time and memory
- Rollback to `target.args=0` and uncomment `assert` in `Task12.java`. Now you should encounter assertion failure which will be reported with line-by-line execution trace by JPF. **Send this log as a result of this task**.
- If you enable `target.args=1`, you effectively will have two logs
  - printed by your program (readable, but slows down the analysis)
  - printed by JPF (quite exhaustive, but require some practice to comprehend)

If you are interested in more advanced overview of JPF, but not yet ready to open the documentation, consider watching this introductory videos (content is the same, language is different):
- "Java Path Finder: летим на Марс без багов и дедлоков" [link](https://youtu.be/sQSwShW_IlI?si=ZMlKKLQxMZYhk1T7)
- "Java Path Finder: going to Mars without bugs and deadlocks" [link](https://youtu.be/dgHbSL_aDs0?si=vi81xieQ4zKRECQG)

## Requirements

Just send required logs to the lecturer, this task needed to confirm you are able to run JPF locally.
