# Task 3.2 (Lost signal)

## Description

There is a sketch implementation of "two producer, single consumer" concurrent system. It uses a lock-guarded static `Object` field to pass the data items between threads, looks like author of the code tried to implement `BlockingQueue` with `CAPACITY=1`.

Please find at least two bugs in this implementation:
- Describe concurrent execution trace in which consumer thread is blocked arbitrary long (starvation, no progress)
- Describe concurrent execution trace in which producer assigned `object1` to `data` but consumer did not processed it. In other words, consumer processed some `object2` that was assigned to `data` AFTER assignment of `object1` (lost data)
- The same as previous but you are not allowed to use spurious wakeups
- If you find more abnormal situations, they will be graded with additional points

```java

static Object data = null;
static Lock lock = new ReentrantLock();

static void producer1() {
  lock.lock(); 
  try { 
    while (true) {
      if (data != null) {
        condition.await();
      }
      data = new Data1(); 
      condition.signal();
    }
  } finally { 
    lock.unlock(); 
  } 
}

static void producer2() { 
  lock.lock(); 
  try { 
    while (true) {
      if (data != null) {
        condition.await();
      }
      data = new Data2(); 
      condition.signal();
    }
  } finally { 
    lock.unlock(); 
  }
}

static void consumer() { 
  lock.lock(); 
  try { 
    while (true) {
      if (data != null) { 
        process(data); 
        data = null; 
        condition.signal(); 
      }
      condition.await();
    }
  } finally { 
    lock.unlock(); 
  }
}

public static void main() {
  Thread P1 = new Thread(() -> { producer1() });
  Thread P2 = new Thread(() -> { producer2() });
  Thread C  = new Thread(() -> { consumer()  });
  P1.start(); P2.start(); C.start();
  P1.join(); P2.join(); C.join();
}
```

## Requirements

Your answer should use definition of concurrent execution trace and exploit total ordering of events. Please note that all code blocks in this task are guarded with mutex which basically means you could use interleaving model for rigorous reasoning about the concurrent program.
