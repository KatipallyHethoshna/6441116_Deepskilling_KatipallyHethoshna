public interface Stock {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();

    // convenience—for changing the price externally
    void setPrice(double newPrice);
    double getPrice();
    String getSymbol();
}
