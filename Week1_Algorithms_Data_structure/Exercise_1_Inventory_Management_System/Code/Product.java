class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    public Product(int id, String name, int qty, double price) {
        this.productId = id;
        this.productName = name;
        this.quantity = qty;
        this.price = price;
    }

    public String toString() {
        return "[" + productId + "] " + productName + " - Qty: " + quantity + ", Price: Rs." + price;
    }
}

