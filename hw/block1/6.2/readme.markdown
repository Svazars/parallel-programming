# Task 6.2 (Reachable states via JCStress)

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

Modify `FindStates.java` so that it is equivalent to [task 1.3](https://github.com/Svazars/parallel-programming/tree/main/hw/block1/1.3). Enumerate all reachable states (tuples `x=XXX, y=YYY, z=ZZZ`) and for each experimentally discovered result
- provide execution trace in terms of interleaving model (if possible); or
- explain which compiler optimization could lead to this "unpredictable" result (ensure there is no synchronization point between load/store operations that compiler decided to reorder, we expect that JVM obeys the rules)

**Clarification**: you **do not** need to actually [inspect assembly code](https://github.com/AdoptOpenJDK/jitwatch), just propose any reordering that compiler was allowed to perform

## Requirements

Your solution to this task should contain at least two parts:
- Source code for your experiments (fork the repo and prepare a branch for review)
- Answers to the questions in `pdf` file. Feel free to use any typesetting system (.tex + pdflatex, .markdown + pandoc, LibreOfficeWriter + Export as pdf ...).
