public class Bag {
    private String bagID;
    private String foodTypeMarking;
    private double weight;
    private String itemType;
    private String storageDate;
    private String retrievalDate;
    private String customerID;
    private String status;
    private double totalPrice;
    private String rackLocation;


    public Bag(String bagID, String foodTypeMarking, double weight, String itemType, String storageDate, String customerID) {
        this.bagID = bagID;
        this.foodTypeMarking = foodTypeMarking;
        this.weight = weight;
        this.itemType = itemType;
        this.storageDate = storageDate;
        this.customerID = customerID;
        this.status = "STORED";
        this.rackLocation = "";
        calculatePrice();
    }


    private void calculatePrice() {
        double basePrice = 700.0;
        double extraWeight;
        if (weight > 80) {
            extraWeight = weight - 80;
            totalPrice = basePrice + (extraWeight * 10.0);
        } else {
            totalPrice = basePrice;
        }
    }

    public String getBagID() {
        return bagID;
    }

    public String getFoodTypeMarking() {
        return foodTypeMarking;
    }

    public double getWeight() {
        return weight;
    }

    public String getItemType() {
        return itemType;
    }

    public String getStorageDate() {
        return storageDate;
    }

    public String getRetrievalDate() {
        return retrievalDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getRackLocation() {
        return rackLocation;
    }

    public void setRetrievalDate(String date) {
        this.retrievalDate = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRackLocation(String location) {
        this.rackLocation = location;
    }

    public void displayBagInfo() {
        System.out.println("\n--- Bag Information ---");
        System.out.println("Bag ID: " + bagID);
        System.out.println("Food Marking: " + foodTypeMarking);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Item Type: " + itemType);
        System.out.println("Storage Date: " + storageDate);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Status: " + status);
        System.out.println("Total Price: Rs." + totalPrice);
        System.out.println("Rack Location: " + rackLocation);
        if (retrievalDate != null && !retrievalDate.isEmpty()) {
            System.out.println("Retrieval Date: " + retrievalDate);
        }
    }
}
