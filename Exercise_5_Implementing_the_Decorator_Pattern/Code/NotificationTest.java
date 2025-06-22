public class NotificationTest {
    public static void main(String[] args) {
        // Base notifier
        Notifier notifier = new EmailNotifier();

        // Add SMS functionality
        notifier = new SMSNotifierDecorator(notifier);

        // Add Slack functionality
        notifier = new SlackNotifierDecorator(notifier);

        // Final send will trigger all channels
        notifier.send("Your order #7890 has been shipped!");
    }
}
