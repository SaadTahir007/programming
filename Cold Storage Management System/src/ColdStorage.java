import java.util.*;

public class ColdStorage {
    private Floor[] floors;
    private ArrayList<Customer> customers;
    private ArrayList<Bag> allBags;
    private ArrayList<Transaction> transactions;
    private MachineRoom[] machineRooms;
    private String adminPassword;
    private int transactionCounter;

    public ColdStorage() {
        floors = new Floor[3];
        floors[0] = new Floor(1, 3);
        floors[1] = new Floor(2, 3);
        floors[2] = new Floor(3, 3);

        customers = new ArrayList<>();
        allBags = new ArrayList<>();
        transactions = new ArrayList<>();

        machineRooms = new MachineRoom[3];
        for (int i = 0; i < 3; i++) {
            machineRooms[i] = new MachineRoom("M" + (i + 1), i + 1);
        }

        adminPassword = "admin123";
        transactionCounter = 1;
    }

    public boolean adminLogin(String password) {
        return adminPassword.equals(password);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Bag> getAllBags() {
        return allBags;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public MachineRoom[] getMachineRooms() {
        return machineRooms;
    }

    public void addCustomer(String name, String phone, String address) {
        String customerID = "C" + (customers.size() + 1);
        Customer customer = new Customer(customerID, name, phone, address);
        customers.add(customer);
        System.out.println("Customer added successfully! Customer ID: " + customerID);
    }

    public Customer findCustomer(String customerID) {
        for (Customer c : customers) {
            if (c.getCustomerID().equals(customerID)) {
                return c;
            }
        }
        return null;
    }

    public boolean storeBag(String customerID, String foodMarking, double weight, String itemType, String date) {
        Customer customer = findCustomer(customerID);
        if (customer == null) {
            System.out.println("Customer not found!");
            return false;
        }

        String bagID = "B" + (allBags.size() + 1);
        Bag bag = new Bag(bagID, foodMarking, weight, itemType, date, customerID);

        for (Floor floor : floors) {
            int rackIndex = floor.findEmptyRack();
            if (rackIndex > -1) {
                Rack rack = floor.getRacks()[rackIndex];
                if (rack.canStoreBag(weight)) {
                    int slotIndex = rack.findEmptySlot();
                    if (slotIndex > -1) {
                        rack.storeBag(bag, slotIndex);
                        String location = "Floor " + floor.getFloorNumber() + ", Rack " + rack.getRackID() + ", Slot " + (slotIndex + 1);
                        bag.setRackLocation(location);
                        allBags.add(bag);
                        customer.incrementBagCount();

                        String transID = "T" + transactionCounter++;
                        Transaction trans = new Transaction(transID, bagID, customerID, "STORAGE", date, bag.getTotalPrice());
                        transactions.add(trans);

                        System.out.println("\nBag stored successfully!");
                        System.out.println("Bag ID: " + bagID);
                        System.out.println("Location: " + location);
                        System.out.println("Total Price: Rs." + bag.getTotalPrice());
                        return true;
                    }
                } else {
                    System.out.println("Rack weight capacity exceeded!");
                }
            }
        }

        System.out.println("No suitable space found in storage!");
        return false;
    }

    public void storeMultipleBags(String customerID, int numberOfBags, Scanner scanner) {
        Customer customer = findCustomer(customerID);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        int successCount = 0;
        int failCount = 0;
        double totalCost = 0;

        System.out.println("\n--- Storing " + numberOfBags + " bags ---");

        for (int i = 1; i <= numberOfBags; i++) {
            System.out.println("\n=== Bag " + i + " of " + numberOfBags + " ===");

            System.out.print("Enter food marking (ID): ");
            String marking = scanner.nextLine();

            System.out.print("Enter bag weight (kg): ");
            double weight = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter item type (e.g., Vegetables, Fruits, Grains): ");
            String itemType = scanner.nextLine();

            System.out.print("Enter storage date (DD-MM-YYYY): ");
            String storeDate = scanner.nextLine();

            boolean success = storeBag(customerID, marking, weight, itemType, storeDate);

            if (success) {
                successCount++;
                Bag lastBag = allBags.get(allBags.size() - 1);
                totalCost += lastBag.getTotalPrice();
            } else {
                failCount++;
            }
        }

        System.out.println("\n========== STORAGE SUMMARY ==========");
        System.out.println("Total bags to store: " + numberOfBags);
        System.out.println("Successfully stored: " + successCount);
        System.out.println("Failed to store: " + failCount);
        System.out.println("Total cost: Rs." + totalCost);
        System.out.println("=====================================\n");
    }

    public void retrieveBag(String bagID, String retrievalDate) {
        Bag bag = findBag(bagID);
        if (bag == null) {
            System.out.println("Bag not found!");
            return;
        }

        if (!bag.getStatus().equals("STORED")) {
            System.out.println("Bag already retrieved!");
            return;
        }

        double totalAmount = bag.getTotalPrice();

        String location = bag.getRackLocation();
        removeBagFromRack(bag);

        bag.setRetrievalDate(retrievalDate);
        bag.setStatus("RETRIEVED");

        Customer customer = findCustomer(bag.getCustomerID());
        if (customer != null) {
            customer.decrementBagCount();
        }

        String transID = "T" + transactionCounter++;
        Transaction trans = new Transaction(transID, bagID, bag.getCustomerID(), "RETRIEVAL", retrievalDate, totalAmount);
        transactions.add(trans);

        displayInvoice(bag, totalAmount, retrievalDate);
    }

    public void retrieveMultipleBags(int numberOfBags, String retrievalDate, Scanner scanner) {
        int successCount = 0;
        int failCount = 0;
        double totalAmount = 0;
        ArrayList<String> retrievedBagIDs = new ArrayList<>();

        System.out.println("\n--- Retrieving " + numberOfBags + " bags ---");

        for (int i = 1; i <= numberOfBags; i++) {
            System.out.println("\n=== Bag " + i + " of " + numberOfBags + " ===");
            System.out.print("Enter bag ID to retrieve: ");
            String bagID = scanner.nextLine();

            Bag bag = findBag(bagID);

            if (bag == null) {
                System.out.println("Bag " + bagID + " not found!");
                failCount++;
                continue;
            }

            if (!bag.getStatus().equals("STORED")) {
                System.out.println("Bag " + bagID + " already retrieved!");
                failCount++;
                continue;
            }

            double amount = bag.getTotalPrice();
            removeBagFromRack(bag);
            bag.setRetrievalDate(retrievalDate);
            bag.setStatus("RETRIEVED");

            Customer customer = findCustomer(bag.getCustomerID());
            if (customer != null) {
                customer.decrementBagCount();
            }

            String transID = "T" + transactionCounter++;
            Transaction trans = new Transaction(transID, bagID, bag.getCustomerID(), "RETRIEVAL", retrievalDate, amount);
            transactions.add(trans);

            retrievedBagIDs.add(bagID);
            totalAmount += amount;
            successCount++;

            System.out.println(" Bag " + bagID + " retrieved successfully! Amount: Rs." + amount);
        }

        if (successCount > 0) {
            displayMultipleInvoice(retrievedBagIDs, totalAmount, retrievalDate, successCount, failCount, numberOfBags);
        }

        System.out.println("\n========== RETRIEVAL SUMMARY ==========");
        System.out.println("Total bags to retrieve: " + numberOfBags);
        System.out.println("Successfully retrieved: " + successCount);
        System.out.println("Failed to retrieve: " + failCount);
        System.out.println("Total amount: Rs." + totalAmount);
        System.out.println("=======================================\n");
    }

    private void displayMultipleInvoice(ArrayList<String> bagIDs, double totalAmount, String date, int success, int fail, int total) {
        System.out.println("\n========== COLLECTIVE INVOICE ==========");
        System.out.println("Retrieval Date: " + date);
        System.out.println("Total Bags Retrieved: " + success + " out of " + total);
        System.out.println("\nBag IDs Retrieved:");
        for (String id : bagIDs) {
            Bag bag = findBag(id);
            System.out.println(" - " + id + " (" + bag.getFoodTypeMarking() + ") - Rs." + bag.getTotalPrice());
        }
        System.out.println("------------------------------------------");
        System.out.println("TOTAL AMOUNT: Rs." + totalAmount);
        System.out.println("==========================================\n");
    }

    public Bag findBag(String bagID) {
        for (Bag b : allBags) {
            if (b.getBagID().equals(bagID)) {
                return b;
            }
        }
        return null;
    }

    public void searchBagByMarking(String foodMarking) {
        boolean found = false;
        for (Bag b : allBags) {
            if (b.getFoodTypeMarking().equalsIgnoreCase(foodMarking) && b.getStatus().equals("STORED")) {
                b.displayBagInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bag found with marking: " + foodMarking);
        }
    }

    private void removeBagFromRack(Bag bag) {
        String location = bag.getRackLocation();

        for (Floor floor : floors) {
            for (Rack rack : floor.getRacks()) {
                for (int i = 0; i < rack.getTotalSlots(); i++) {
                    Slot slot = rack.getSlots()[i];
                    if (!slot.isEmpty() && slot.getBagStored().getBagID().equals(bag.getBagID())) {
                        rack.removeBag(i);
                        return;
                    }
                }
            }
        }
    }

    private void displayInvoice(Bag bag, double amount, String date) {
        System.out.println("\n========== INVOICE ==========");
        System.out.println("Date: " + date);
        System.out.println("Bag ID: " + bag.getBagID());
        System.out.println("Food Marking: " + bag.getFoodTypeMarking());
        System.out.println("Customer ID: " + bag.getCustomerID());
        System.out.println("Storage Date: " + bag.getStorageDate());
        System.out.println("Retrieval Date: " + date);
        System.out.println("Weight: " + bag.getWeight() + " kg");
        System.out.println("----------------------------");
        System.out.println("TOTAL AMOUNT: Rs." + amount);
        System.out.println("============================\n");
    }

    public void getCustomerBags(String customerID) {
        boolean found = false;
        System.out.println("\n--- Bags for Customer " + customerID + " ---");
        for (Bag b : allBags) {
            if (b.getCustomerID().equals(customerID) && b.getStatus().equals("STORED")) {
                System.out.println("Bag ID: " + b.getBagID() + " | Marking: " + b.getFoodTypeMarking() + " | Location: " + b.getRackLocation());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No active bags found for this customer.");
        }
    }

    public void displayStorageStatistics() {
        int totalSlots = 0;
        int occupiedSlots = 0;
        double totalRevenue = 0;

        for (Floor floor : floors) {
            for (Rack rack : floor.getRacks()) {
                totalSlots += rack.getTotalSlots();
                occupiedSlots += (rack.getTotalSlots() - rack.getEmptySlotCount());
            }
        }

        for (Transaction t : transactions) {
            if (t.getType().equals("RETRIEVAL")) {
                totalRevenue += t.getAmount();
            }
        }

        System.out.println("\n===== STORAGE STATISTICS =====");
        System.out.println("Total Slots: " + totalSlots);
        System.out.println("Occupied Slots: " + occupiedSlots);
        System.out.println("Empty Slots: " + (totalSlots - occupiedSlots));
        System.out.println("Occupancy Rate: " + String.format("%.2f", (occupiedSlots * 100.0 / totalSlots)) + "%");
        System.out.println("Total Customers: " + customers.size());
        System.out.println("Total Bags Stored: " + allBags.size());
        System.out.println("Active Bags: " + getActiveBagCount());
        System.out.println("Total Revenue: Rs." + totalRevenue);
        System.out.println("==============================\n");
    }

    private int getActiveBagCount() {
        int count = 0;
        for (Bag b : allBags) {
            if (b.getStatus().equals("STORED")) count++;
        }
        return count;
    }

    public void displayFloorDetails(int floorNumber) {
        if (floorNumber < 1 || floorNumber > 3) {
            System.out.println("Invalid floor number!");
            return;
        }

        Floor floor = floors[floorNumber - 1];
        System.out.println("\n--- Floor " + floorNumber + " Details ---");

        for (Rack rack : floor.getRacks()) {
            System.out.println("\nRack ID: " + rack.getRackID());
            System.out.println("Empty Slots: " + rack.getEmptySlotCount() + "/" + rack.getTotalSlots());
            System.out.println("Current Weight: " + rack.getCurrentWeight() + "/" + rack.getMaxWeightCapacity() + " kg");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("\n===== TRANSACTION HISTORY =====");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            Collections.reverse(transactions);
            for (Transaction t : transactions) {
                t.displayTransaction();
            }
            Collections.reverse(transactions);
        }
        System.out.println("===============================\n");
    }

    public void displayMachineStatus() {
        System.out.println("\n===== MACHINE ROOM STATUS =====");
        for (MachineRoom m : machineRooms) {
            m.displayStatus();
        }
        System.out.println("===============================\n");
    }

    public void toggleMachine(int machineNumber) {
        if (machineNumber < 1 || machineNumber > 3) {
            System.out.println("Invalid machine number!");
            return;
        }
        MachineRoom machine = machineRooms[machineNumber - 1];
        if (machine.isOn()) {
            machine.turnOff();
        } else {
            machine.turnOn();
        }
    }
}