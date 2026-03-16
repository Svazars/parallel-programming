public class b {

    public static void main(String[] args) throws Exception {
        Thread b = B();
        Thread a = A(b);
        Thread d = D(a);

        a.start();
        d.start();

        a.join();
        d.join();

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
    private static Thread D(Thread b) {
        return new Thread(() -> {
            try {
                b.join();
            } catch (InterruptedException e) {
                System.out.println("B finished with the exception, which was not handled in thread D");
            }
        });
    }
}