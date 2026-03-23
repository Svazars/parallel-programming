# Task 6.1 (JCStress introduction)

## Description

### How to start development

Command line:
```bash
mvn clean verify
java -jar target/jcstress.jar
```

IntelliJ IDEA: open .pom as project, use Java 11 for [project source level](https://www.jetbrains.com/help/idea/project-settings-and-structure.html#language-level) 
and [target bytecode level](https://www.jetbrains.com/help/idea/java-compiler.html).

### Task

Build and run `TestConcurrentIncrements` tests on your local computer. 
- Do you observe `(1, 1)` in your environment?
- Is it OK if `(1, 1)` result would not empirically appear on some device?

Create subclass inside `TestConcurrentIncrements` (see [JCStress nesting](https://github.com/openjdk/jcstress/blob/master/jcstress-samples/src/main/java/org/openjdk/jcstress/samples/api/API_04_Nesting.java)) that runs 5 concurrent actors, each increments the same `int` variable. Use [@Arbiter](https://github.com/openjdk/jcstress/blob/master/jcstress-samples/src/main/java/org/openjdk/jcstress/samples/api/API_02_Arbiters.java) to collect the final result (`1, 2, 3, 4 or 5`).
- Do you observe `0` in your environment? Should you?
- Do you observe `1` in your environment? Should you?
- Do you observe `5` in your environment? Should you?

**Note**: by default JCStress creates a lot of stress-testing setups and you could suffer from combinatorial explosion (`ETA for test is several days`). Consider reading `java -jar target/jcstress.jar --help`.

## Requirements

Your solution to this task should contain at least two parts:
- Source code for your experiments (fork the repo and prepare a branch for review)
- Answers to the questions in `pdf` file. Feel free to use any typesetting system (.tex + pdflatex, .markdown + pandoc, LibreOfficeWriter + Export as pdf ...).
