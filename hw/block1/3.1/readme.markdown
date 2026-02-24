# Task 3.1 (ThreadPerTaskExecutorService + JoinFuture)

## Description

Implement the following classes
```java
class JoinFuture<V> {
  
  /**
   * Waits if necessary for the computation to complete, and then retrieves its result.
   * 
   * Returns:
   *   the computed result
   * 
   * Throws:   
   *   ExecutionException - if the computation threw an exception
   * 
  */
  public V get() throws ExecutionException { ??? }

  /**
   * Returns `true` if this task completed. Completion may be due to normal termination or 
   * an exception -- in all of these cases, this method will return true. 
  */
  public boolean isDone() { ??? }
}

class ThreadPerTaskExecutorService {    
  public ThreadPerTaskExecutorService(ThreadFactory f) { ??? }

  /**
   * Submits a value-returning task for execution and returns a `JoinFuture` representing the pending results of the task.
   * The `JoinFuture`s `get` method will return the task's result upon successful completion.
   */
  <T> JoinFuture<T> submit(Callable<T> task) { ??? }
}
```

There are some constraints for this programming assignment:
- You are not allowed to use `java.util.concurrent` classes.
- You are not allowed to use `volatile` fields.
- You are not allowed to use `synchronized` keyword.
- You could add new fields to classes above.
- You could add utility inner classes to classes above.
- You could use `java.util` collections if required.

**Important**: provide at least 5 tests for your solution.

## Requirements

Provide source code for your class and unit tests, document key parts of your solution with javadoc, explain why your solution is correct in several sentences. Be ready to answer questions about deadlock-freedom, starvation-freedom, visibility of concurrently modified fields in your code.
