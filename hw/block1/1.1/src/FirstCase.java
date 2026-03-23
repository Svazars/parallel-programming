public class FirstCase {
    public static void main(String[] args) {
        Thread threadB = new Thread(() -> {
            throw new RuntimeException();
        });

        threadB.start();
        try {
            threadB.join();
        }
        catch (RuntimeException | InterruptedException e) {
            System.err.println("in A from B");
            System.err.println(e);
        }
        System.out.println("A joined B");
    }
}