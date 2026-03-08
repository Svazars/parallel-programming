# Task 4.4 (Semaphore)

## Description

Implement the following class
```java
/**
 * A counting semaphore. Conceptually, a semaphore maintains a set of permits. 
 * 
 * Each acquire() blocks if necessary until a permit is available, and then takes it. 
 * Each release() adds a permit, potentially releasing a blocking acquirer. 
 * 
 * However, no actual permit objects are used; the Semaphore just keeps a count of the number available and acts accordingly.
*/ 
class MySemaphore {
	/**
	 * Creates a Semaphore with the given number of permits.
	*/
	public MySemaphore(int permits);

	/**
	 * Acquires a permit, if one is available and returns immediately, reducing the number of available permits by one.
	 * 
	 * If no permit is available then the current thread becomes disabled for thread scheduling purposes and lies dormant 
	 * until some other thread invokes the release() method for this semaphore and the current thread is next to be assigned a permit.
	*/ 
	public void acquire();

	/**
	 * Releases a permit, increasing the number of available permits by one. If any threads are trying to acquire a permit, 
	 * then one is selected and given the permit that was just released. That thread is (re)enabled for thread scheduling purposes.
	 * 
	 * There is no requirement that a thread that releases a permit must have acquired that permit by calling acquire(). 
	 * Correct usage of a semaphore is established by programming convention in the application.
	*/
	public void release();  
}
```

There are some constraints for this programming assignment:
- You are allowed to use `java.util.concurrent.Lock`, `java.util.concurrent.Condition`.
- You are allowed to use `synchronized` keyword.
- You are not allowed to use `volatile` fields.
- You could use `java.util` collections if required.

**Important**: provide at least 5 tests for your solution.

## Requirements

Provide source code for your class and unit tests, document key parts of your solution with javadoc, explain why your solution is correct in several sentences. Be ready to answer questions about deadlock-freedom, starvation-freedom, visibility of concurrently modified fields in your code.
