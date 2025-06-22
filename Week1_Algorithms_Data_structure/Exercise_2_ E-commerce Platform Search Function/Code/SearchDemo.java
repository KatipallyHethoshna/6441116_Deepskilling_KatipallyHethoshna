import java.util.Arrays;
import java.util.Comparator;

public class SearchDemo {

    // Linear Search: O(n) time complexity
    public static Product linearSearch(Product[] products, int targetId) {
        for (Product product : products) {
            if (product.productId == targetId) {
                return product;
            }
        }
        return null;
    }

    // Binary Search: O(log n) time complexity (array must be sorted by productId)
    public static Product binarySearch(Product[] products, int targetId) {
        int low = 0, high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (products[mid].productId == targetId) {
                return products[mid];
            } else if (products[mid].productId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Shirt", "Clothing"),
            new Product(103, "Book", "Education"),
            new Product(104, "Mobile", "Electronics"),
            new Product(105, "Shoes", "Footwear")
        };

        // Sort for binary search
        Arrays.sort(products, Comparator.comparingInt(p -> p.productId));

        int searchId = 104;

        // Linear Search
        Product result1 = linearSearch(products, searchId);
        System.out.println("Linear Search Result: " + (result1 != null ? result1 : "Product not found"));

        // Binary Search
        Product result2 = binarySearch(products, searchId);
        System.out.println("Binary Search Result: " + (result2 != null ? result2 : "Product not found"));
    }
}
