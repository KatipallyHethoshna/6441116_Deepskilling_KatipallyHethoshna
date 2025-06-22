public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromRemoteServer();
    }

    private void loadFromRemoteServer() {
        System.out.println("Loading image from remote server: " + fileName);
        // Simulate delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Loading interrupted.");
        }
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
