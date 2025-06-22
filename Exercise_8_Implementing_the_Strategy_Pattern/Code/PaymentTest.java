public class PaymentTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Use Credit Card payment
        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "Hethoshna"));
        context.payAmount(1500.00);

        // Switch to PayPal payment
        context.setPaymentStrategy(new PayPalPayment("hethosexample@email.com"));
        context.payAmount(3200.50);
    }
}
