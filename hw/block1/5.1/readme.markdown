# Task 5.1 (ExecutorService with shutdown)

## Description

Assume you have the following interfaces implemented (e.g. from task 3.1)
```
interface MyFuture<V> {
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
  public V get() throws ExecutionException;

  /**
   * Returns `true` if this task completed. Completion may be due to normal termination or 
   * an exception -- in all of these cases, this method will return true. 
  */
  public boolean isDone();
}

interface MyExecutorService {    
  /**
   * Submits a value-returning task for execution and returns a `MyFuture` representing the pending results of the task.
   * The `MyFuture`s `get` method will return the task's result upon successful completion.
   */
  <T> MyFuture<T> submit(Callable<T> task);
}
```

Implement the following wrapper class
```java
class MyExecutorServiceWithShutdown {

  private final MyExecutorService service;

  public MyExecutorServiceWithShutdown(MyExecutorService service) { 
    this.service = service;
    ...
  }

  /**
   * Forwarder to `this.service.submit`.
   * 
   * Throws `IllegalArgumentException` if user tries to submit task after `shutdown`.
  */
  <T> MyFuture<T> submit(Callable<T> task);

  /**
   * Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted. 
   * Invocation has no additional effect if already shut down.
   * 
   * This method does not wait for previously submitted tasks to complete execution. Use `awaitTermination` to do that.
   *
  */  
  void shutdown();

  /**
   * Returns true if this executor has been shut down.
   * 
   * True does not mean all submitted tasks has been completed. Use `isTerminated` to check that.
   *
  */
  boolean isShutdown();

  /**
   * Returns true if all tasks have completed following shut down.
   * Note that isTerminated is never true unless either shutdown or shutdownNow was called first.  
   * 
  */
  boolean isTerminated();

  /**
   * Forbids submission of new tasks (equivalent to `shutdown`), halts the processing of waiting tasks and 
   * returns a list of the tasks that were awaiting execution.
   * 
   * This method does not wait for actively executing tasks to terminate. Any already executing task **will not** be returned
   * by this method. Use `awaitTermination` to ensure all tasks are finished.
   * 
  */
  List<Callable<?>> shutdownNow();

  /**
   * Blocks until all tasks have completed execution after a shutdown request.
   *
  */ 
  boolean awaitTermination();
```

Please use concurrent state machine pattern to properly implement this class.
`enum State { Created, AcceptingTasks, UnderShutdown, Terminated }` is a good place to start your design.

There are some constraints for this programming assignment:
- You are allowed to use `java.util.concurrent.*` classes from the lectures.
- You are allowed to use `synchronized` keyword.
- You are not allowed to use `volatile` fields.
- You are not allowed to use `java.util.concurrent.ExecutorService` wrappers/factory methods to implement the task in "one line of code".
- You could use `java.util` collections if required.

**Important**: provide at least 5 tests for your solution.

## Requirements

Provide source code for your class and unit tests, document key parts of your solution with javadoc, explain why your solution is correct in several sentences.