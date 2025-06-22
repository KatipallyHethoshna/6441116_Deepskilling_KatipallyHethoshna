public class GatewayTwoAdapter implements PaymentProcessor {
    private GatewayTwoService gatewayTwo;

    public GatewayTwoAdapter() {
        gatewayTwo = new GatewayTwoService();
    }

    @Override
    public void processPayment(double amount) {
        gatewayTwo.executePayment("INR", amount);
    }
}
