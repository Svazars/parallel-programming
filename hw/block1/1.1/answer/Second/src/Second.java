class ThreadA extends Thread {
    public Thread childThread;
    ThreadA(String name) { super(name); }

    @Override
    public void run() {
        Thread B = new ThreadB("B");
        childThread = B;
        B.start();
        try {
            B.join();                                           // Thread B will be dead after exception
        } catch (InterruptedException ignored) {}               // Exception in thread B won't affect thread A
        System.out.printf("Process %s finished\n", getName());  // Because stderr and stdout are asynchronous,
                                                                // sometimes this line will be printed before
                                                                // error message about exception in thread B.
    }
}

class ThreadB extends Thread {
    ThreadB(String name) { super(name); }

    @Override
    public void run() {
        int a = 5 / 0;
        System.out.printf("Process %s finished\n", getName());
    }
}

class ThreadC extends Thread {
    private final Thread target;
    ThreadC(String name, Thread target) { super(name); this.target = target; }

    @Override
    public void run() {
        try {
            target.join();                                      // If thread C joins thread B after exception happened,
                                                                // thread C won't wait for thread B to die because
                                                                // it is already dead.
        } catch (InterruptedException ignored) {}
        System.out.printf("Process %s finished\n", getName());
    }
}

public class Second {
    public static void main(String[] args) throws Exception {
        ThreadA A = new ThreadA("A");
        A.start(); A.join();
        Thread C = new ThreadC("C", A.childThread);
        C.start(); C.join();
    }
}
