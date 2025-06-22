public class TestBuilder {
    public static void main(String[] args) {
        // Basic computer
        Computer basic = new Computer.Builder()
                            .setCpu("Intel i3")
                            .setRam(8)
                            .setStorage(256)
                            .build();

        System.out.println("Basic Configuration:");
        basic.showSpecs();

        // High-end computer
        Computer gaming = new Computer.Builder()
                            .setCpu("AMD Ryzen 9")
                            .setRam(32)
                            .setStorage(1024)
                            .setGraphics("NVIDIA RTX 4070")
                            .build();

        System.out.println("\nGaming Configuration:");
        gaming.showSpecs();
    }
}
