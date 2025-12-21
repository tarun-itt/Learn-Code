import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomerRepository {

    private final List<Customer> customers;

    public CustomerRepository(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> searchByCountry(String country) {
        return search(customer -> customer.getCountry().contains(country));
    }

    public List<Customer> searchByCompanyName(String companyName) {
        return search(customer -> customer.getCompanyName().contains(companyName));
    }

    public List<Customer> searchByContactName(String contactName) {
        return search(customer -> customer.getContactName().contains(contactName));
    }

    private List<Customer> search(CustomerFilter filter) {
        List<Customer> matchedCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            if (filter.matches(customer)) {
                matchedCustomers.add(customer);
            }
        }

        matchedCustomers.sort(
                Comparator.comparing(Customer::getCustomerId)
        );

        return matchedCustomers;
    }
}
