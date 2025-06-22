public class DIApp {
    public static void main(String[] args) {
        // Injecting dependency
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        // Using the service
        service.displayCustomer("C001");
    }
}