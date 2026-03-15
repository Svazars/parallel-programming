class Task13 {
    public static void main(String... args) {
        var rand = new java.util.Random(42);
        if (rand.nextBoolean()) {
            System.out.println("Hello true model checking world!");
        } else {
            System.out.println("Hello fake model checking world!");
        }
    }
}
