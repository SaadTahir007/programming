import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ColdStorage storage = new ColdStorage();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("===================================");
        System.out.println("  COLD STORAGE MANAGEMENT SYSTEM  ");
        System.out.println("===================================\n");

        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        if (!storage.adminLogin(password)) {
            running = false;
            System.out.println("Invalid password! Access denied.");
        } else {
            System.out.println("Login successful! Welcome Admin.\n");
        }

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    storage.addCustomer(name, phone, address);
                    break;

                case 2:
                    System.out.println("\n--- Store Bag Options ---");
                    System.out.println("1. Store Single Bag");
                    System.out.println("2. Store Multiple Bags");
                    System.out.print("Enter your choice: ");
                    int storeChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (storeChoice == 1) {
                        System.out.print("Enter customer ID: ");
                        String customerID = scanner.nextLine();
                        System.out.print("Enter food marking (ID): ");
                        String marking = scanner.nextLine();
                        System.out.print("Enter bag weight (kg): ");
                        double weight = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter item type (e.g., Vegetables, Fruits, Grains): ");
                        String itemType = scanner.nextLine();
                        System.out.print("Enter storage date (DD-MM-YYYY): ");
                        String storeDate = scanner.nextLine();
                        storage.storeBag(customerID, marking, weight, itemType, storeDate);
                    } else if (storeChoice == 2) {
                        System.out.print("Enter customer ID: ");
                        String customerID = scanner.nextLine();
                        System.out.print("Enter number of bags to store: ");
                        int numBags = scanner.nextInt();
                        scanner.nextLine();
                        storage.storeMultipleBags(customerID, numBags, scanner);
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Retrieve Bag Options ---");
                    System.out.println("1. Retrieve Single Bag");
                    System.out.println("2. Retrieve Multiple Bags");
                    System.out.print("Enter your choice: ");
                    int retrieveChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (retrieveChoice == 1) {
                        System.out.print("Enter bag ID: ");
                        String bagID = scanner.nextLine();
                        System.out.print("Enter retrieval date (DD-MM-YYYY): ");
                        String retDate = scanner.nextLine();
                        storage.retrieveBag(bagID, retDate);
                    } else if (retrieveChoice == 2) {
                        System.out.print("Enter number of bags to retrieve: ");
                        int numBags = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter retrieval date (DD-MM-YYYY): ");
                        String retDate = scanner.nextLine();
                        storage.retrieveMultipleBags(numBags, retDate, scanner);
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;

                case 4:
                    System.out.print("Enter bag ID: ");
                    String searchID = scanner.nextLine();
                    Bag bag = storage.findBag(searchID);
                    if (bag != null) {
                        bag.displayBagInfo();
                    } else {
                        System.out.println("Bag not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter food marking: ");
                    String searchMarking = scanner.nextLine();
                    storage.searchBagByMarking(searchMarking);
                    break;

                case 6:
                    System.out.print("Enter customer ID: ");
                    String customerID = scanner.nextLine();
                    storage.getCustomerBags(customerID);
                    break;

                case 7:
                    System.out.print("Enter customer ID: ");
                    String customerInfoID = scanner.nextLine();
                    Customer customer = storage.findCustomer(customerInfoID);
                    if (customer != null) {
                        customer.displayCustomerInfo();
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;

                case 8:
                    storage.displayStorageStatistics();
                    break;

                case 9:
                    System.out.print("Enter floor number (1-3): ");
                    int floorNum = scanner.nextInt();
                    scanner.nextLine();
                    storage.displayFloorDetails(floorNum);
                    break;

                case 10:
                    storage.displayTransactionHistory();
                    break;

                case 11:
                    storage.displayMachineStatus();
                    break;

                case 12:
                    System.out.print("Enter machine number (1-3): ");
                    int machineNum = scanner.nextInt();
                    scanner.nextLine();
                    storage.toggleMachine(machineNum);
                    break;

                case 0:
                    System.out.println("Thank you for using Cold Storage Management System!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1.  Add Customer");
        System.out.println("2.  Store Bag (Single/Multiple)");
        System.out.println("3.  Retrieve Bag (Single/Multiple)");
        System.out.println("4.  Search Bag by ID");
        System.out.println("5.  Search Bag by Food Marking");
        System.out.println("6.  View Customer's Bags");
        System.out.println("7.  View Customer Information");
        System.out.println("8.  Display Storage Statistics");
        System.out.println("9.  Display Floor Details");
        System.out.println("10. Display Transaction History");
        System.out.println("11. View Machine Room Status");
        System.out.println("12. Toggle Machine ON/OFF");
        System.out.println("0.  Exit");
        System.out.println("====================");
    }
}