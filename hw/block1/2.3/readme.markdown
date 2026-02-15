# Task 2.3

## Description

Assume you have the following interface
```java
interface NonReentrantLock {
  void lock();
  void unlock();
}
```
that throws `IllegalMonitorStateException` when thread tries to repeatedly lock the same instance. 

Assume you have thread-safe `NonReentrantLockFactory` that allows you to create as many `NonReentrantLock`s as you need:
```java
interface NonReentrantLockFactory {
  NonReentrantLock create();  
}
```

Implement reentrant mutex in `MyReentrantLock` class
```java
class MyReentrantLock {
  
  private final NonReentrantLockFactory factory;

  public MyReentrantLock(NonReentrantLockFactory factory) {
    this.factory = factory;
    // TODO: implement me
  }

  public void lock() {
    // TODO: implement me
  }

  public void unlock() {
    // TODO: implement me
  }
}
```

- You are not allowed to use `java.util.concurrent` classes.
- You are not allowed to use `volatile` fields.
- You are not allowed to use `synchronized` keyword.
- You could use `Thread.currentThread()` to obtain unique identifier of current thread.
- You could add new fields to `MyReentrantLock` class.
- You could add utility inner classes to `MyReentrantLock` class.
- You could use `java.util` collections if required.
- Your `unlock` implementation should throw `IllegalMonitorStateException` if the current thread is not the holder of this lock.
- Any unsynchronized access to shared data is treated as a bug. "Benign data race" is not allowed!

Do not forget to provide unit tests for your solution. **Clarification**: of course you could use `java.util.concurrent` to write proper tests and provide high-quality mocks for `NonReentrantLock`.

**Hint 1**: you could use special technique called ''spinning'' (google "spin-lock"). If your lock data structure has `boolean tryLock` method then `lock` method could just call `tryLock` in infinite loop.

**Hint 2**: you could use more elaborate algorithm to avoid useless burning of CPU cycles (google "backoff policy"). It will grant you additional score for this task.

## Requirements

Provide source code for your class and unit tests, document key parts of your solution with javadoc, explain why your solution is correct in several sentences. Be ready to answer questions about deadlock-freedom and starvation-freedom of your code.
