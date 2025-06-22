import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {

    private final String symbol;
    private final List<Observer> observers = new ArrayList<>();
    private double price;

    public StockMarket(String symbol, double initialPrice) {
        this.symbol = symbol;
        this.price  = initialPrice;
    }

    // — Stock interface —
    @Override
    public void registerObserver(Observer o) {
        if (o != null && !observers.contains(o)) observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(symbol, price);
        }
    }

    @Override
    public void setPrice(double newPrice) {
        if (newPrice != price) {
            this.price = newPrice;
            notifyObservers();
        }
    }

    @Override
    public double getPrice() { return price; }

    @Override
    public String getSymbol() { return symbol; }
}
