public class PaymentSystem {
    public static void main(String[] args) {
        PaymentProcessor processor1 = new GatewayOneAdapter();
        processor1.processPayment(1500);

        PaymentProcessor processor2 = new GatewayTwoAdapter();
        processor2.processPayment(2200);
    }
}
