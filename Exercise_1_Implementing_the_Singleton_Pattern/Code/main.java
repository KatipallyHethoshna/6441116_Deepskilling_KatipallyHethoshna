public class main {
    public static void main(String[] args) {
        logger logger1 = logger.getInstance();
        logger logger2 = logger.getInstance();

        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 are the same instance.");
        } else {
            System.out.println("Different instances created!");
        }
    }
}