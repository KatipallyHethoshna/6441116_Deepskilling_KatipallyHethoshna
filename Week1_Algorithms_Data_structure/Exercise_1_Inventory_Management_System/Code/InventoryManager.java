import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<Product> inventory = new ArrayList<>();

    // Add a new product
    public void addProduct(Product product) {
        inventory.add(product);
        System.out.println("Product added: " + product);
    }

    // Update a product by ID
    public boolean updateProduct(int productId, int newQty, double newPrice) {
        for (Product p : inventory) {
            if (p.productId == productId) {
                p.quantity = newQty;
                p.price = newPrice;
                System.out.println("Product updated: " + p);
                return true;
            }
        }
        System.out.println("Product not found.");
        return false;
    }

    // Delete a product by ID
    public boolean deleteProduct(int productId) {
        for (Product p : inventory) {
            if (p.productId == productId) {
                inventory.remove(p);
                System.out.println("Product deleted: " + p);
                return true;
            }
        }
        System.out.println("Product not found.");
        return false;
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Product p : inventory) {
            System.out.println(p);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        // Add products
        manager.addProduct(new Product(101, "Laptop", 10, 55000));
        manager.addProduct(new Product(102, "Mobile", 20, 15000));

        // Display
        manager.displayInventory();

        // Update product
        manager.updateProduct(102, 25, 14000);

        // Delete product
        manager.deleteProduct(101);

        // Display final inventory
        manager.displayInventory();
    }
}
