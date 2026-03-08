# Task 3.4 (Bounded concurrent queue with single condition)

## Description

Inspect the following implementation of bounded concurrent queue:
```java
class BoundedBuffer<E> {
  private final Lock l = new ReentrantLock(); 
  private final Condition c = l.newCondition(); 
  private final Queue<E> q = new LinkedList<>();
  private final int MAX_CAPACITY;

  public BoundedBuffer(int capacity) {
    MAX_CAPACITY = capacity;
  }

  void put(E x) { 
    l.lock();
    try {
      while (q.size() == MAX_CAPACITY) {
        c.await();
      }
      q.add(x);
      c.signal();
    } finally {
      l.unlock();
    }
  }

  E take() {
    lock.lock();
    try {
      while (q.isEmpty()) {
        c.await();
      }
      c.signal();
      return q.remove();
    } finally {
      l.unlock();
    }
  }
}
```

Prove that for any `MAX_CAPACITY > 2` there exists concurrent execution scenario where `take` operation blocks indefinitely despite the fact that `queue` is not empty.

## Requirements

Your proof should be in `pdf` file. Feel free to use any typesetting system (.tex + pdflatex, .markdown + pandoc, LibreOfficeWriter + Export as pdf ...).
