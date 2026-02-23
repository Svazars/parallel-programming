class ThreadA extends Thread {
    ThreadA(String name) { super(name); }

    @Override
    public void run() {
        Thread B = new ThreadB("B");
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

class ThreadD extends Thread {
    private final Thread target;
    ThreadD(String name, Thread target) { super(name); this.target = target; }

    @Override
    public void run() {
        try {
            target.join();                                      // If thread D joins thread A, thread D will wait for thread A to die.
                                                                // Even though an uncaught exception killed thread B, thread A finishes normally.
                                                                // Therefore, thread D will successfully return from join() and finish normally too.
                                                                // thread D will wait for thread A to finish.
        } catch (InterruptedException ignored) {}
        System.out.printf("Process %s finished\n", getName());
    }
}

public class Third {
    public static void main(String[] args) throws Exception {
        ThreadA A = new ThreadA("A");
        Thread D = new ThreadD("D", A);
        A.start(); D.start();
        A.join(); D.join();
    }
}
