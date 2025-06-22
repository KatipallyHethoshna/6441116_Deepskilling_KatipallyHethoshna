public class WebApp implements Observer {
    private final String siteName;

    public WebApp(String siteName) { this.siteName = siteName; }

    @Override
    public void update(String symbol, double newPrice) {
        System.out.printf("[Web] %s dashboard now shows %s = Rs%.2f%n",
                          siteName, symbol, newPrice);
    }
}
