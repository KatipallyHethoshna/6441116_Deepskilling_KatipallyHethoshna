import java.util.*;

public class Library {
    private Book[] books;
    private int size;

    public Library(int capacity) {
        books = new Book[capacity];
        size = 0;
    }

    public void addBook(Book b) {
        if (size < books.length) {
            books[size++] = b;
        } else {
            System.out.println("Library full.");
        }
    }

    // Linear search
    public void searchTitleLinear(String searchTerm) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (books[i].title.equalsIgnoreCase(searchTerm)) {
                books[i].showInfo();
                found = true;
            }
        }
        if (!found) System.out.println("Title not found.");
    }

    // Binary search (on sorted books array)
    public void searchTitleBinary(String searchTitle) {
        Arrays.sort(books, 0, size, Comparator.comparing(b -> b.title.toLowerCase()));
        int start = 0, end = size - 1;
        boolean matched = false;

        while (start <= end) {
            int mid = (start + end) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(searchTitle);

            if (cmp == 0) {
                books[mid].showInfo();
                matched = true;
                break;
            } else if (cmp < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (!matched) System.out.println("Title not found.");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Library lib = new Library(10);

        // Add sample books
        lib.addBook(new Book(101, "Java Basics", "Ravi"));
        lib.addBook(new Book(102, "Algorithms", "Deepa"));
        lib.addBook(new Book(103, "Networking", "Amit"));
        lib.addBook(new Book(104, "Data Structures", "Reema"));

        int choice;
        do {
            System.out.println("\n1. Linear Search by Title\n2. Binary Search by Title\n3. Exit");
            System.out.print("Select: ");
            choice = scan.nextInt();
            scan.nextLine(); // flush

            if (choice == 1 || choice == 2) {
                System.out.print("Enter title to search: ");
                String title = scan.nextLine();
                if (choice == 1) {
                    lib.searchTitleLinear(title);
                } else {
                    lib.searchTitleBinary(title);
                }
            } else if (choice != 3) {
                System.out.println("Invalid option.");
            }

        } while (choice != 3);

        scan.close();
    }
}
