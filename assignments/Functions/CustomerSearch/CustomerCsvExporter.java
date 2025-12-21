import java.util.List;

public class CustomerCsvExporter {

    public String export(List<Customer> customers) {
        StringBuilder csvBuilder = new StringBuilder();

        for (Customer customer : customers) {
            appendCustomer(csvBuilder, customer);
        }

        return csvBuilder.toString();
    }

    private void appendCustomer(
            StringBuilder csvBuilder,
            Customer customer
    ) {
        csvBuilder
                .append(customer.getCustomerId()).append(",")
                .append(customer.getCompanyName()).append(",")
                .append(customer.getContactName()).append(",")
                .append(customer.getCountry())
                .append("\n");
    }
}
