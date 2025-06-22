public class Computer {
    // Required and optional parts
    private String cpu;
    private int ram;
    private int storage;
    private String graphics;

    // Private constructor
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphics = builder.graphics;
    }

    public void showSpecs() {
        System.out.println("CPU: " + cpu);
        System.out.println("RAM: " + ram + " GB");
        System.out.println("Storage: " + storage + " GB");
        System.out.println("Graphics: " + (graphics != null ? graphics : "None"));
    }

    // Static nested Builder class
    public static class Builder {
        private String cpu;
        private int ram;
        private int storage;
        private String graphics;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(int storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGraphics(String graphics) {
            this.graphics = graphics;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
