public class OrderSorter {

    // Bubble Sort: O(n^2)
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    // Swap
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort: O(n log n) average, O(n^2) worst
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice < pivot) {
                i++;
                // Swap
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        // Swap pivot to correct position
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    // Display Orders
    public static void displayOrders(Order[] orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order(1, "Alice", 1500.50),
            new Order(2, "Bob", 750.75),
            new Order(3, "Charlie", 2300.00),
            new Order(4, "Daisy", 1200.00)
        };

        // Clone for both sorting
        Order[] bubbleSortedOrders = orders.clone();
        Order[] quickSortedOrders = orders.clone();

        System.out.println("Original Orders:");
        displayOrders(orders);

        // Bubble Sort
        bubbleSort(bubbleSortedOrders);
        System.out.println("\nOrders after Bubble Sort:");
        displayOrders(bubbleSortedOrders);

        // Quick Sort
        quickSort(quickSortedOrders, 0, quickSortedOrders.length - 1);
        System.out.println("\nOrders after Quick Sort:");
        displayOrders(quickSortedOrders);
    }
}
