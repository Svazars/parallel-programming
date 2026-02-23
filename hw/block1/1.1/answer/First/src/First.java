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

public class First {
    public static void main(String[] args) throws Exception {
        ThreadA A = new ThreadA("A");
        A.start(); A.join();
    }
}
