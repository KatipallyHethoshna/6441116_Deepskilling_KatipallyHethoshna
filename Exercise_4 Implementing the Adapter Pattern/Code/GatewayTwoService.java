public class GatewayTwoService {
    public void executePayment(String currency, double value) {
        System.out.println("GatewayTwo: Processed " + currency + " " + value);
    }
}
