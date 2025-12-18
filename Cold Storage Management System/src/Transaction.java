public class Transaction {
    private String transactionID;
    private String bagID;
    private String customerID;
    private String type;
    private String date;
    private double amount;

    public Transaction(String transactionID, String bagID, String customerID, String type, String date, double amount) {
        this.transactionID = transactionID;
        this.bagID = bagID;
        this.customerID = customerID;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public void displayTransaction() {
        System.out.println("Transaction ID: " + transactionID + " | Type: " + type + " | Bag ID: " + bagID + " | Date: " + date + " | Amount: Rs." + amount);
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getBagID() {
        return bagID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}