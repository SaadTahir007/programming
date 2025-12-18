public class Customer {
    private String customerID;
    private String name;
    private String phone;
    private String address;
    private int totalBagsStored;

    public Customer(String customerID, String name, String phone, String address) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.totalBagsStored = 0;
    }

    public void incrementBagCount() {
        totalBagsStored++;
    }

    public void decrementBagCount() {
        totalBagsStored--;
    }


    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getTotalBagsStored() {
        return totalBagsStored;
    }

    public void displayCustomerInfo() {
        System.out.println("\n--- Customer Information ---");
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Total Bags Stored: " + totalBagsStored);
    }
}