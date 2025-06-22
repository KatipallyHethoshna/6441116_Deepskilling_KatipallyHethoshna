public class GatewayOneAdapter implements PaymentProcessor {
    private GatewayOneAPI gatewayOne;

    public GatewayOneAdapter() {
        gatewayOne = new GatewayOneAPI();
    }

    @Override
    public void processPayment(double amount) {
        gatewayOne.makeTransaction(amount);
    }
}
