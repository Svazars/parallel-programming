# Task 4.2 (MyMonitor)

## Description

Implement the following class
```java
class MyMonitor {

  /**
   * Acquires the monitor lock.
   * Acquires the lock if it is not held by another thread and returns immediately, setting the lock hold count to one.
   * If the current thread already holds the lock then the hold count is incremented by one and the method returns immediately.
   * If the lock is held by another thread then the current thread becomes disabled for thread scheduling purposes and lies dormant 
   * until the lock has been acquired, at which time the lock hold count is set to one.
  */
  public void monitorEnter();

  /**
   * Attempts to release this lock.
   * If the current thread is the holder of this lock then the hold count is decremented. 
   * If the hold count is now zero then the lock is released. 
   * If the current thread is not the holder of this lock then IllegalMonitorStateException is thrown.
  */
  public void monitorExit();

  /**
   * Causes the current thread to wait until it is awakened, typically by being notified.
   * 
   * The current thread must own this monitor lock. Throws IllegalMonitorStateException if the current thread is not the owner of the 
   * monitor. See the notify method for a description of the ways in which a thread can become the owner of a monitor lock.
   * 
   * This method causes the current thread (referred to here as T) to place itself in the wait set for this monitor and then to 
   * relinquish any and all synchronization claims on this object. Note that only the locks on this monitor are relinquished; any 
   * other objects on which the current thread may be synchronized remain locked while the thread waits.
   * 
   * Thread T then becomes disabled for thread scheduling purposes and lies dormant until one of the following occurs:
   * 
   * - Some other thread invokes the notify method for this monitor and thread T happens to be arbitrarily chosen as the thread to be awakened.
   * - Some other thread invokes the notifyAll method for this monitor.
   * - Thread T is awakened spuriously. (See below.) 
   * 
   * The thread T is then removed from the wait set for this monitor and re-enabled for thread scheduling. It competes in the usual 
   * manner with other threads for the right to synchronize on the object; once it has regained control of the object, all its
   * synchronization claims on the object are restored to the status quo ante - that is, to the situation as of the time that the
   * wait method was invoked. Thread T then returns from the invocation of the wait method. Thus, on return from the wait method, 
   * the synchronization state of the object and of thread T is exactly as it was when the wait method was invoked.
   * 
   * A thread can wake up without being notified, a so-called spurious wakeup. While this will rarely occur in practice, applications
   * must guard against it by testing for the condition that should have caused the thread to be awakened, and continuing to wait if
   * the condition is not satisfied.
   * 
   * For more information on this topic, see section 14.2, "Condition Queues," in Brian Goetz and others' 
   * Java Concurrency in Practice (Addison-Wesley, 2006) or Item 69 in Joshua Bloch's Effective Java, Second Edition (Addison-Wesley, 2008). 
  */
  public void monitorWait();

  /**
   * Wakes up a single thread that is waiting on this object's monitor. If any threads are waiting on this object, one of them is
   * chosen to be awakened. The choice is arbitrary and occurs at the discretion of the implementation. A thread waits on monitor by 
   * calling wait method.
   * 
   * The awakened thread will not be able to proceed until the current thread relinquishes the lock on this object. The awakened 
   * thread will compete in the usual manner with any other threads that might be actively competing to synchronize on this monitor;
   * for example, the awakened thread enjoys no reliable privilege or disadvantage in being the next thread to lock this object.
   * 
   * This method should only be called by a thread that is the owner of this monitor. A thread becomes the owner of the 
   * monitor by executing a monitorEnter of that monitor without pairing monitorExit.
   * 
   * Only one thread at a time can own a monitor.
  */ 
  public void monitorNotify();

  /** 
   * Wakes up all threads that are waiting on this monitor. A thread waits on monitor by calling wait method.
   * 
   * The awakened threads will not be able to proceed until the current thread relinquishes the lock on this object. 
   * The awakened threads will compete in the usual manner with any other threads that might be actively competing to synchronize
   * on this monitor; for example, the awakened threads enjoy no reliable privilege or disadvantage in being the next thread to lock
   * this monitor.
   * 
   * This method should only be called by a thread that is the owner of this monitor. See the notify method for a description of 
   * the ways in which a thread can become the owner of a monitor.
  */
  public void monitorNotifyAll();
}
```

There are some constraints for this programming assignment:
- You are allowed to use `java.util.concurrent.Lock`, `java.util.concurrent.Condition`.
- You are not allowed to use `volatile` fields.
- You are not allowed to use `synchronized` keyword.
- You could use `java.util` collections if required.

**Important**: provide at least 5 tests for your solution.

### Hard mode

Implement `MyMonitor` that does not suffer from spurious wakeups.

## Requirements

Provide source code for your class and unit tests, document key parts of your solution with javadoc, explain why your solution is correct in several sentences. Be ready to answer questions about deadlock-freedom, starvation-freedom, visibility of concurrently modified fields in your code.
