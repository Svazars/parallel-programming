public class b {

    public static void main(String[] args) throws Exception {
        Thread b = B();
        Thread a = A(b);
        Thread c = C(b);

        a.start();
        c.start();

        a.join();
        c.join();

        System.out.println("main thread finished");
    }

    private static Thread B() {
        return new Thread(() -> {
            throw new RuntimeException("Exception from thread b");
        });
    }

    private static Thread A(Thread b) {
        return new Thread(() -> {
            b.start();
            try {
                b.join();
            } catch (InterruptedException e) {
                System.out.println("B finished with the exception, which was not handled in thread A");
            }
        });
    }
    private static Thread C(Thread b) {
        return new Thread(() -> {
            try {
                b.join();
            } catch (InterruptedException e) {
                System.out.println("B finished with the exception, which was not handled in thread C");
            }
        });
    }
}