public class SingleThreaded {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().join();
    }
}
