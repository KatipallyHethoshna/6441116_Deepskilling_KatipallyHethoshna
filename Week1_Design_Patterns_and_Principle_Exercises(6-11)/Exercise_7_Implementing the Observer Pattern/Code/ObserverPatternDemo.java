public class ObserverPatternDemo {
    public static void main(String[] args) {

        // Create subject
        StockMarket nifty = new StockMarket("NIFTY50", 22500.00);

        // Create observers
        Observer aliceMobile = new MobileApp("Alice");
        Observer traderWeb   = new WebApp("TraderPro");

        // Register observers
        nifty.registerObserver(aliceMobile);
        nifty.registerObserver(traderWeb);

        // Price changes → everyone is notified
        nifty.setPrice(22580.25);
        nifty.setPrice(22690.55);

        // Unsubscribe one observer
        nifty.removeObserver(traderWeb);

        // Only Alice gets this update
        nifty.setPrice(22710.10);
    }
}
