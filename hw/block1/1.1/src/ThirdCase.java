class TThread extends Thread {
    @Override
    public void run() {
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

public class ThirdCase {

    public static void main(String[] args) {
        Thread threadA = new TThread();
        threadA.start();
        try {
            threadA.join();
        }
        catch (InterruptedException | RuntimeException e) {
            System.err.println("in D from A");
            System.err.println(e);
        }
        System.out.println("D joined A");
    }
}
