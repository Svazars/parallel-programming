# Task 4.1

## Description

### 4.1.1

```java
static long x;
static long y;
static final Object lockX = new Object();
static final Object lockY = new Object();

public static void increment() {
  final boolean isX = Thread.currentThread().getId() % 2 == 0;
  final var lock = isX ? lockX : lockY;
  synchronized(lock) {
    if (isX) {
      x++;
    } else {
      y++;
    }
  }
}

public static void get() {
  long a;
  long b;
  synchronized(lockX) {
    a = y;
  }
  synchronized(lockY) {
    b = x;
  }
  return a + b;
}

```

Is this counter thread-safe?

### 4.1.2

```java
static final Object lock = new Object();

public static void foo(Runnable r) {
  synchronized(lock) {
    r.run();
  }
}

public static void bar() {
  // does nothing
}

public static void baz() {
  var t = new Thread() {
    public void run() {
      foo(bar);
    }
  };
  t.start();
  t.join();
}

public static void main() {
    foo(baz);
}
```
Could you find concurrency related problems in this program?

### 4.1.3

```java
public static void foo(Object o, long c) {
  synchronized(o) {
    if (c > 0) {
      foo(o, c - 1);
    }
  }
}

static final Object lock = new Object();

public static void main() {
    foo(lock, Random.nextInt(0, 100_000));
}
```

Do you expect this program to work?

### 4.1.4
```java
static int x;
static final Object lock = new Object();

public static int increment(int delta) {
  synchronized(lock) {
    x += delta;
    return x;
  }
}

static class Holder {
  int data;
}

public static int get() {
  var h = new Holder();
  var t = new Thread() {
      public void run() {
        h.data = increment(0);
      }
  }
  t.start();
  t.join();
  return h.data;
}
```

Is this implementation of multithreaded counter is thread-safe? Are there any data races on `Holder.data`?

## Requirements

Ensure your answer is clear, readable and concise.
