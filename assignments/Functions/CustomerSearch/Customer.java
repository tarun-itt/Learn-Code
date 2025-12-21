public class Customer {

    private String customerId;
    private String companyName;
    private String contactName;
    private String country;

    public Customer(
            String customerId,
            String companyName,
            String contactName,
            String country
    ) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.contactName = contactName;
        this.country = country;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getCountry() {
        return country;
    }
}
