public class ImageViewerTest {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("pic1.jpg");
        Image image2 = new ProxyImage("pic2.jpg");
        Image image3 = new ProxyImage("pic1.jpg"); // Same as image1

        // Load images
        image1.display(); // Loads from server
        System.out.println();

        image2.display(); // Loads from server
        System.out.println();

        image3.display(); // Should use cache
    }
}
