public class MobileApp implements Observer {
    private final String owner;

    public MobileApp(String owner) { this.owner = owner; }

    @Override
    public void update(String symbol, double newPrice) {
        System.out.printf("[Mobile] %s sees %s at Rs%.2f%n", owner, symbol, newPrice);
    }
}
