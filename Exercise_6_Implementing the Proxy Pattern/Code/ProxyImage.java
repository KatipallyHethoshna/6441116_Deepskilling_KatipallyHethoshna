import java.util.HashMap;
import java.util.Map;

public class ProxyImage implements Image {
    private String fileName;
    private static Map<String, RealImage> cache = new HashMap<>();

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        RealImage realImage = cache.get(fileName);
        if (realImage == null) {
            realImage = new RealImage(fileName); // Lazy loading
            cache.put(fileName, realImage);      // Caching
        } else {
            System.out.println("Loaded from cache: " + fileName);
        }

        realImage.display();
    }
}
