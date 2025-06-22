public class CustomerService {
    private CustomerRepository customerRepository;

    // Constructor injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void displayCustomer(String id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer != null) {
            System.out.println("Customer Found:");
            System.out.println("ID: " + customer.getId());
            System.out.println("Name: " + customer.getName());
        } else {
            System.out.println("Customer not found!");
        }
    }
}
