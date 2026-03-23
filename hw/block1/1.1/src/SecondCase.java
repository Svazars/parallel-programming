public class SecondCase {
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

        Thread threadC = new Thread(() -> {
            try {
                threadB.join();
            } catch (InterruptedException | RuntimeException e) {
                System.err.println("in C from B");
                System.err.println(e);
            }
            System.out.println("C joined B");
        });

        threadC.start();
        try {
            threadC.join();
        } catch (InterruptedException | RuntimeException e) {
            System.err.println("in A from C");
            System.err.println(e);
        }
        System.out.println("A joined C");
    }
}
