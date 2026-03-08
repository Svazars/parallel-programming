public class TwoThreaded {
    static volatile Runnable lambda = null;
    public static void main(String[] args) throws Exception{
        Thread A = new Thread(() -> { lambda.run(); });
        Thread B = new Thread(() -> {
            try {
                A.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        lambda = () -> {
            try {
                B.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        A.start(); B.start();
        A.join(); B.join();
    }
}
