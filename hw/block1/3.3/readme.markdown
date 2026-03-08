# Task 3.3 (CondVarFuture + SingleTheadExecutorService)

## Description

Implement the following classes
```java
class CondVarFuture<V> {
  
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

/**
 * Creates an ExecutorService that uses a single worker thread operating off an unbounded queue. 
 * Note however that if this single thread terminates due to a failure during execution, a new one will take its place
 * if needed to execute subsequent tasks.
 * 
 * Tasks are guaranteed to execute sequentially, and no more than one task will be active at any given time.
 */
class SingleTheadExecutorService {    
  public SingleTheadExecutorService(ThreadFactory f) { ??? }

  /**
   * Submits a value-returning task for execution and returns a `CondVarFuture` representing the pending results of the task.
   * The `CondVarFuture`s `get` method will return the task's result upon successful completion.
   */
  <T> CondVarFuture<T> submit(Callable<T> task) { ??? }
}
```

There are some constraints for this programming assignment:
- You are allowed to use `java.util.concurrent.Lock`, `java.util.concurrent.Condition` and `java.util.concurrent.LinkedBlockingQueue` classes.
- You are not allowed to use other `java.util.concurrent` classes.
- You are not allowed to use `volatile` fields.
- You are not allowed to use `synchronized` keyword.
- You could add new fields to classes above.
- You could add utility inner classes to classes above.
- You could use `java.util` collections if required.
- Please remember that `Callable<T>` could throw checked `Exception` -- in such a case worker thread should properly handle it: catch, mark `Future` as throwing `ExecutionException`, continue executing other tasks in the same thread.
- Please remember that `Callable<T>` could throw unchecked `Throwable` (including very severe problems) -- in such a case worker thread should try its best to properly handle it: mark `Future` as throwing `ExecutionException`, unblock any waiting threads in `Future.get` method, but then this thread should terminate. Note, however, that `SingleTheadExecutorService` should continue to execute other submitted tasks, e.g. new `Thread` should be started via provided `ThreadFactory`.

**Important**: provide at least 5 tests for your solution.

## Requirements

Provide source code for your class and unit tests, document key parts of your solution with javadoc, explain why your solution is correct in several sentences. Be ready to answer questions about deadlock-freedom, starvation-freedom, visibility of concurrently modified fields in your code, guarantees about single-threaded execution of all tasks.
