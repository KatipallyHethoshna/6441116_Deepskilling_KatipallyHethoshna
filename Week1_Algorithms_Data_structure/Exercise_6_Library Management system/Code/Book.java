public class Book {
    int bookId;
    String title;
    String author;

    public Book(int id, String t, String a) {
        bookId = id;
        title = t;
        author = a;
    }

    public void showInfo() {
        System.out.println("[" + bookId + "] \"" + title + "\" by " + author);
    }
}
