import java.util.ArrayList;

class Task12 {

    private static boolean ENABLED_LOGGING = false;
    private static ArrayList<String> trace = new ArrayList<>();
    private static synchronized void log(String msg) {
        if (!ENABLED_LOGGING) {
            return;
        }
        trace.add(msg);
    }

    private static synchronized String trace() {
        if (!ENABLED_LOGGING) {
            return "";
        }
        String result = "";
        for (var s : trace) {
            result += s + " -> ";
        }
        result += "END";
        return result;
    }

    static int x = 0;
    static int A_r_y = 0;
    static int B_r_z = 0;

    static class A extends Thread {
        public void run() {
            int y = -1;     log("A.1");
            int a_x = x;    log("A.2");
            if (a_x == 0) { log("A.3==0");
                y = x;      log("A.4");
                x = y + 1;  log("A.5");
            } else {        log("A.3!=0"); }
            A_r_y = y;
        }
    }

    static class B extends Thread {
        public void run() {
            int z = -1;     log("B.1");
            int b_x = x;    log("B.2");
            if (b_x == 0) { log("B.3==0");
                z = x;      log("B.4");
                x = z + 1;  log("B.5");
            } else {        log("B.3!=0"); }
            B_r_z = z;
        }
    }

    public static void main(String... args) throws InterruptedException {
        if (args.length > 0) {
            ENABLED_LOGGING = Integer.parseInt(args[0]) != 0;
        }

        Thread a = new A();
        Thread b = new B();

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.printf("x = %2d, r_y = %2d, r_z = %2d | %s\n", x, A_r_y, B_r_z, trace());

        // TODO: uncomment me and also change Task12.jpf (target.args=1)
        //if (x == 2 && A_r_y == 0 && B_r_z == 1) {
        //  assert false;
        //}
    }
}
