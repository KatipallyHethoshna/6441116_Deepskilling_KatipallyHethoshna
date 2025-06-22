public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(String id) {
        // Dummy data simulation
        return new Customer(id, "John Doe");
    }
}
