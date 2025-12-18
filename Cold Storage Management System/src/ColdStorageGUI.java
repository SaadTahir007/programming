import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ColdStorageGUI extends Application {
    private ColdStorage coldStorage;
    private Stage primaryStage;

    private final String PRIMARY_COLOR = "#2980b9";
    private final String BACKGROUND_COLOR = "#ecf0f1";
    private final String TEXT_COLOR = "#2c3e50";

    @Override
    public void start(Stage stage) {
        coldStorage = new ColdStorage();
        primaryStage = stage;

        primaryStage.setTitle("Cold Storage Management System");
        primaryStage.setScene(createLoginScene());
        primaryStage.setWidth(900);
        primaryStage.setHeight(650);
        primaryStage.show();
    }

    private Scene createLoginScene() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        root.setPadding(new Insets(50));

        Label titleLabel = new Label("Cold Storage System");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web(PRIMARY_COLOR));

        Label subtitleLabel = new Label("Admin Login");
        subtitleLabel.setFont(Font.font("Arial", 16));

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(200);

        Button loginButton = createStyledButton("Login");

        Label hintLabel = new Label("(Default password: admin123)");
        hintLabel.setFont(Font.font("Arial", 11));
        hintLabel.setTextFill(Color.GRAY);

        loginButton.setOnAction(e -> {
            String password = passwordField.getText();
            if (coldStorage.adminLogin(password)) {
                primaryStage.setScene(createMainMenuScene());
                passwordField.clear();
            } else {
                showAlert("Error", "Invalid Password!", Alert.AlertType.ERROR);
            }
        });

        root.getChildren().addAll(titleLabel, subtitleLabel, passwordLabel, passwordField, loginButton, hintLabel);

        return new Scene(root);
    }

    private Scene createMainMenuScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Main Menu");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web(PRIMARY_COLOR));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(15);
        buttonGrid.setVgap(15);
        buttonGrid.setPadding(new Insets(30));

        Button customerBtn = createMenuButton("Customer Management");
        Button storageBtn = createMenuButton("Store Single Bag");
        Button multiStorageBtn = createMenuButton("Store Multiple Bags");
        Button retrievalBtn = createMenuButton("Retrieve Single Bag");
        Button multiRetrievalBtn = createMenuButton("Retrieve Multiple Bags");
        Button searchBtn = createMenuButton("Search Bags");
        Button reportsBtn = createMenuButton("View Reports");
        Button machineBtn = createMenuButton("Machine Control");
        Button exitBtn = createMenuButton("Logout");

        customerBtn.setOnAction(e -> primaryStage.setScene(createCustomerScene()));
        storageBtn.setOnAction(e -> primaryStage.setScene(createStorageScene()));
        multiStorageBtn.setOnAction(e -> primaryStage.setScene(createMultipleStorageScene()));
        retrievalBtn.setOnAction(e -> primaryStage.setScene(createRetrievalScene()));
        multiRetrievalBtn.setOnAction(e -> primaryStage.setScene(createMultipleRetrievalScene()));
        searchBtn.setOnAction(e -> primaryStage.setScene(createSearchScene()));
        reportsBtn.setOnAction(e -> primaryStage.setScene(createReportsScene()));
        machineBtn.setOnAction(e -> showMachineControl());
        exitBtn.setOnAction(e -> primaryStage.setScene(createLoginScene()));

        buttonGrid.add(customerBtn, 0, 0);
        buttonGrid.add(storageBtn, 1, 0);
        buttonGrid.add(multiStorageBtn, 0, 1);
        buttonGrid.add(retrievalBtn, 1, 1);
        buttonGrid.add(multiRetrievalBtn, 0, 2);
        buttonGrid.add(searchBtn, 1, 2);
        buttonGrid.add(reportsBtn, 0, 3);
        buttonGrid.add(machineBtn, 1, 3);
        buttonGrid.add(exitBtn, 0, 4);

        root.setCenter(buttonGrid);

        return new Scene(root);
    }

    private Scene createCustomerScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Customer Management");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web(PRIMARY_COLOR));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(30));
        formBox.setStyle("-fx-background-color: white; -fx-border-color: " + PRIMARY_COLOR + "; -fx-border-width: 2;");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter name");
        nameField.setMaxWidth(300);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter phone");
        phoneField.setMaxWidth(300);

        TextField addressField = new TextField();
        addressField.setPromptText("Enter address");
        addressField.setMaxWidth(300);

        Button addButton = createStyledButton("Add Customer");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(5);
        resultArea.setMaxWidth(400);

        addButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                resultArea.setText("ERROR: Please fill all fields!");
                return;
            }

            String customerID = "C" + (coldStorage.getCustomers().size() + 1);
            coldStorage.addCustomer(name, phone, address);
            resultArea.setText("Customer added successfully!\n\n" + "Customer ID: " + customerID + "\n" + "Name: " + name + "\n" + "Phone: " + phone + "\n" + "Address: " + address);

            nameField.clear();
            phoneField.clear();
            addressField.clear();
        });

        formBox.getChildren().addAll(new Label("Name:"), nameField, new Label("Phone:"), phoneField, new Label("Address:"), addressField, addButton, resultArea);

        root.setCenter(formBox);

        Button backButton = createStyledButton("Back to Main Menu");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(10));
        root.setBottom(backButton);

        return new Scene(root);
    }

    private Scene createStorageScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Store Single Bag");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web(PRIMARY_COLOR));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(30));
        formBox.setStyle("-fx-background-color: white; -fx-border-color: " + PRIMARY_COLOR + "; -fx-border-width: 2;");

        TextField customerIDField = new TextField();
        customerIDField.setPromptText("Enter customer ID");
        customerIDField.setMaxWidth(300);

        TextField markingField = new TextField();
        markingField.setPromptText("Enter food marking");
        markingField.setMaxWidth(300);

        TextField weightField = new TextField();
        weightField.setPromptText("Enter weight in kg");
        weightField.setMaxWidth(300);

        TextField itemTypeField = new TextField();
        itemTypeField.setPromptText("Enter item type");
        itemTypeField.setMaxWidth(300);

        TextField dateField = new TextField();
        dateField.setPromptText("Enter date (DD-MM-YYYY)");
        dateField.setMaxWidth(300);

        Button storeButton = createStyledButton("Store Bag");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(8);
        resultArea.setMaxWidth(500);

        storeButton.setOnAction(e -> {
            try {
                String customerID = customerIDField.getText().trim();
                String marking = markingField.getText().trim();
                double weight = Double.parseDouble(weightField.getText().trim());
                String itemType = itemTypeField.getText().trim();
                String date = dateField.getText().trim();

                if (customerID.isEmpty() || marking.isEmpty() || itemType.isEmpty() || date.isEmpty()) {
                    resultArea.setText("ERROR: Please fill all fields!");
                    return;
                }

                Customer customer = coldStorage.findCustomer(customerID);
                if (customer == null) {
                    resultArea.setText("ERROR: Customer not found!");
                    return;
                }

                String bagID = "B" + (coldStorage.getAllBags().size() + 1);
                boolean success = coldStorage.storeBag(customerID, marking, weight, itemType, date);

                if (success) {
                    Bag bag = coldStorage.findBag(bagID);
                    resultArea.setText("BAG STORED SUCCESSFULLY!\n\n" + "Bag ID: " + bag.getBagID() + "\n" + "Food Marking: " + bag.getFoodTypeMarking() + "\n" + "Weight: " + bag.getWeight() + " kg\n" + "Item Type: " + bag.getItemType() + "\n" + "Storage Date: " + bag.getStorageDate() + "\n" + "Location: " + bag.getRackLocation() + "\n" + "Total Price: Rs." + bag.getTotalPrice());

                    customerIDField.clear();
                    markingField.clear();
                    weightField.clear();
                    itemTypeField.clear();
                    dateField.clear();
                } else {
                    resultArea.setText("ERROR: No suitable space found or rack capacity exceeded!");
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("ERROR: Invalid weight format!");
            }
        });

        formBox.getChildren().addAll(new Label("Customer ID:"), customerIDField, new Label("Food Marking:"), markingField, new Label("Weight (kg):"), weightField, new Label("Item Type:"), itemTypeField, new Label("Date (DD-MM-YYYY):"), dateField, storeButton, resultArea);

        root.setCenter(formBox);

        Button backButton = createStyledButton("Back to Main Menu");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(10));
        root.setBottom(backButton);

        return new Scene(root);
    }

    private Scene createMultipleStorageScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Store Multiple Bags");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web(PRIMARY_COLOR));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));

        VBox inputBox = new VBox(10);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(20));
        inputBox.setStyle("-fx-background-color: white; -fx-border-color: " + PRIMARY_COLOR + "; -fx-border-width: 2;");

        TextField customerIDField = new TextField();
        customerIDField.setPromptText("Enter customer ID");
        customerIDField.setMaxWidth(300);

        TextField numberOfBagsField = new TextField();
        numberOfBagsField.setPromptText("Enter number of bags");
        numberOfBagsField.setMaxWidth(300);

        Button startButton = createStyledButton("Start Adding Bags");

        inputBox.getChildren().addAll(new Label("Customer ID:"), customerIDField, new Label("Number of Bags:"), numberOfBagsField, startButton);

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(15);
        resultArea.setMaxWidth(600);

        startButton.setOnAction(e -> {
            try {
                String customerID = customerIDField.getText().trim();
                int numberOfBags = Integer.parseInt(numberOfBagsField.getText().trim());

                if (customerID.isEmpty() || numberOfBags <= 0) {
                    resultArea.setText("ERROR: Please enter valid customer ID and number of bags!");
                    return;
                }

                Customer customer = coldStorage.findCustomer(customerID);
                if (customer == null) {
                    resultArea.setText("ERROR: Customer not found!");
                    return;
                }

                resultArea.setText("Storing " + numberOfBags + " bags for Customer: " + customerID + "\n");

                int successCount = 0;
                int failCount = 0;
                double totalCost = 0;
                StringBuilder summary = new StringBuilder();

                for (int i = 1; i <= numberOfBags; i++) {
                    TextInputDialog dialog1 = new TextInputDialog();
                    dialog1.setTitle("Bag " + i);
                    dialog1.setHeaderText("Enter food marking:");
                    String marking = dialog1.showAndWait().orElse(null);
                    if (marking == null) break;

                    TextInputDialog dialog2 = new TextInputDialog();
                    dialog2.setTitle("Bag " + i);
                    dialog2.setHeaderText("Enter weight (kg):");
                    String weightStr = dialog2.showAndWait().orElse(null);
                    if (weightStr == null) break;

                    TextInputDialog dialog3 = new TextInputDialog();
                    dialog3.setTitle("Bag " + i);
                    dialog3.setHeaderText("Enter item type:");
                    String itemType = dialog3.showAndWait().orElse(null);
                    if (itemType == null) break;

                    TextInputDialog dialog4 = new TextInputDialog();
                    dialog4.setTitle("Bag " + i);
                    dialog4.setHeaderText("Enter date (DD-MM-YYYY):");
                    String date = dialog4.showAndWait().orElse(null);
                    if (date == null) break;

                    try {
                        double weight = Double.parseDouble(weightStr);
                        String bagID = "B" + (coldStorage.getAllBags().size() + 1);
                        boolean success = coldStorage.storeBag(customerID, marking, weight, itemType, date);

                        if (success) {
                            Bag bag = coldStorage.findBag(bagID);
                            successCount++;
                            totalCost += bag.getTotalPrice();
                            summary.append(" Bag ").append(i).append(" (").append(bagID).append(") - Stored at ").append(bag.getRackLocation()).append(" - Rs.").append(bag.getTotalPrice()).append("\n");
                        } else {
                            failCount++;
                            summary.append(" Bag ").append(i).append(" - FAILED (No space)\n");
                        }
                    } catch (NumberFormatException ex) {
                        failCount++;
                        summary.append(" Bag ").append(i).append(" - FAILED (Invalid weight)\n");
                    }
                }

                resultArea.setText("======== STORAGE SUMMARY ========\n\n" + summary.toString() + "\n" + "Total Bags: " + numberOfBags + "\n" + "Successfully Stored: " + successCount + "\n" + "Failed: " + failCount + "\n" + "Total Cost: Rs." + totalCost + "\n" + "================================");

                customerIDField.clear();
                numberOfBagsField.clear();

            } catch (NumberFormatException ex) {
                resultArea.setText("ERROR: Invalid number of bags!");
            }
        });

        contentBox.getChildren().addAll(inputBox, resultArea);
        root.setCenter(contentBox);

        Button backButton = createStyledButton("Back to Main Menu");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(10));
        root.setBottom(backButton);

        return new Scene(root);
    }

    private Scene createRetrievalScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Retrieve Single Bag");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web(PRIMARY_COLOR));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(30));
        formBox.setStyle("-fx-background-color: white; -fx-border-color: " + PRIMARY_COLOR + "; -fx-border-width: 2;");

        TextField bagIDField = new TextField();
        bagIDField.setPromptText("Enter bag ID");
        bagIDField.setMaxWidth(300);

        TextField dateField = new TextField();
        dateField.setPromptText("Enter retrieval date (DD-MM-YYYY)");
        dateField.setMaxWidth(300);

        Button retrieveButton = createStyledButton("Retrieve Bag");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(10);
        resultArea.setMaxWidth(500);

        retrieveButton.setOnAction(e -> {
            String bagID = bagIDField.getText().trim();
            String date = dateField.getText().trim();

            if (bagID.isEmpty() || date.isEmpty()) {
                resultArea.setText("ERROR: Please fill all fields!");
                return;
            }

            Bag bag = coldStorage.findBag(bagID);
            if (bag == null) {
                resultArea.setText("ERROR: Bag not found!");
                return;
            }

            if (!bag.getStatus().equals("STORED")) {
                resultArea.setText("ERROR: Bag already retrieved!");
                return;
            }

            double amount = bag.getTotalPrice();
            String customerID = bag.getCustomerID();
            String marking = bag.getFoodTypeMarking();
            String storageDate = bag.getStorageDate();
            double weight = bag.getWeight();

            coldStorage.retrieveBag(bagID, date);

            resultArea.setText("========== INVOICE ==========\n\n" + "Bag ID: " + bagID + "\n" + "Food Marking: " + marking + "\n" + "Customer ID: " + customerID + "\n" + "Storage Date: " + storageDate + "\n" + "Retrieval Date: " + date + "\n" + "Weight: " + weight + " kg\n" + "-----------------------------\n" + "TOTAL AMOUNT: Rs." + amount + "\n" + "=============================");

            bagIDField.clear();
            dateField.clear();
        });

        formBox.getChildren().addAll(new Label("Bag ID:"), bagIDField, new Label("Retrieval Date:"), dateField, retrieveButton, resultArea);

        root.setCenter(formBox);

        Button backButton = createStyledButton("Back to Main Menu");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(10));
        root.setBottom(backButton);

        return new Scene(root);
    }

    private Scene createMultipleRetrievalScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Retrieve Multiple Bags");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web(PRIMARY_COLOR));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));

        VBox inputBox = new VBox(10);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(20));
        inputBox.setStyle("-fx-background-color: white; -fx-border-color: " + PRIMARY_COLOR + "; -fx-border-width: 2;");

        TextField numberOfBagsField = new TextField();
        numberOfBagsField.setPromptText("Enter number of bags");
        numberOfBagsField.setMaxWidth(300);

        TextField dateField = new TextField();
        dateField.setPromptText("Enter retrieval date (DD-MM-YYYY)");
        dateField.setMaxWidth(300);

        Button startButton = createStyledButton("Start Retrieving Bags");

        inputBox.getChildren().addAll(new Label("Number of Bags:"), numberOfBagsField, new Label("Retrieval Date:"), dateField, startButton);

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(15);
        resultArea.setMaxWidth(600);

        startButton.setOnAction(e -> {
            try {
                int numberOfBags = Integer.parseInt(numberOfBagsField.getText().trim());
                String date = dateField.getText().trim();

                if (numberOfBags <= 0 || date.isEmpty()) {
                    resultArea.setText("ERROR: Please enter valid number of bags and date!");
                    return;
                }

                resultArea.setText("Retrieving " + numberOfBags + " bags...\n\n");

                int successCount = 0;
                int failCount = 0;
                double totalAmount = 0;
                StringBuilder summary = new StringBuilder();

                for (int i = 1; i <= numberOfBags; i++) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Retrieval");
                    dialog.setHeaderText("Enter Bag ID " + i + ":");
                    String bagID = dialog.showAndWait().orElse(null);
                    if (bagID == null) break;

                    bagID = bagID.trim();
                    Bag bag = coldStorage.findBag(bagID);

                    if (bag == null) {
                        failCount++;
                        summary.append(" Bag ").append(i).append(" (").append(bagID).append(") - NOT FOUND\n");
                        continue;
                    }

                    if (!bag.getStatus().equals("STORED")) {
                        failCount++;
                        summary.append(" Bag ").append(i).append(" (").append(bagID).append(") - ALREADY RETRIEVED\n");
                        continue;
                    }

                    double amount = bag.getTotalPrice();
                    coldStorage.retrieveBag(bagID, date);

                    successCount++;
                    totalAmount += amount;
                    summary.append(" Bag ").append(i).append(" (").append(bagID).append(") - Retrieved - Rs.").append(amount).append("\n");
                }

                resultArea.setText("======== RETRIEVAL SUMMARY ========\n\n" + summary.toString() + "\n" + "Total Bags: " + numberOfBags + "\n" + "Successfully Retrieved: " + successCount + "\n" + "Failed: " + failCount + "\n" + "TOTAL AMOUNT: Rs." + totalAmount + "\n" + "===================================");

                numberOfBagsField.clear();
                dateField.clear();

            } catch (NumberFormatException ex) {
                resultArea.setText("ERROR: Invalid number of bags!");
            }
        });

        contentBox.getChildren().addAll(inputBox, resultArea);
        root.setCenter(contentBox);

        Button backButton = createStyledButton("Back to Main Menu");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(10));
        root.setBottom(backButton);

        return new Scene(root);
    }

    private Scene createSearchScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Search Bags");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web(PRIMARY_COLOR));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(30));
        contentBox.setStyle("-fx-background-color: white; -fx-border-color: " + PRIMARY_COLOR + "; -fx-border-width: 2;");

        Label searchLabel = new Label("Enter Search Term:");
        TextField searchField = new TextField();
        searchField.setMaxWidth(300);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button searchByMarkingBtn = createStyledButton("Search by Marking");
        Button searchByCustomerBtn = createStyledButton("Search by Customer");

        buttonBox.getChildren().addAll(searchByMarkingBtn, searchByCustomerBtn);

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(15);
        resultArea.setMaxWidth(600);

        searchByMarkingBtn.setOnAction(e -> {
            String marking = searchField.getText().trim();
            if (marking.isEmpty()) {
                resultArea.setText("ERROR: Please enter a food marking!");
                return;
            }

            StringBuilder result = new StringBuilder("Search Results for Marking: " + marking + "\n\n");
            boolean found = false;

            for (Bag b : coldStorage.getAllBags()) {
                if (b.getFoodTypeMarking().equalsIgnoreCase(marking) && b.getStatus().equals("STORED")) {
                    result.append("Bag ID: ").append(b.getBagID()).append("\n");
                    result.append("Food Marking: ").append(b.getFoodTypeMarking()).append("\n");
                    result.append("Weight: ").append(b.getWeight()).append(" kg\n");
                    result.append("Item Type: ").append(b.getItemType()).append("\n");
                    result.append("Storage Date: ").append(b.getStorageDate()).append("\n");
                    result.append("Customer ID: ").append(b.getCustomerID()).append("\n");
                    result.append("Location: ").append(b.getRackLocation()).append("\n");
                    result.append("Price: Rs.").append(b.getTotalPrice()).append("\n");
                    result.append("----------------------------\n");
                    found = true;
                }
            }

            if (!found) {
                result.append("No bags found with marking: ").append(marking);
            }

            resultArea.setText(result.toString());
        });

        searchByCustomerBtn.setOnAction(e -> {
            String customerID = searchField.getText().trim();
            if (customerID.isEmpty()) {
                resultArea.setText("ERROR: Please enter a customer ID!");
                return;
            }

            StringBuilder result = new StringBuilder("Bags for Customer " + customerID + ":\n");
            boolean found = false;

            for (Bag b : coldStorage.getAllBags()) {
                if (b.getCustomerID().equals(customerID) && b.getStatus().equals("STORED")) {
                    result.append("Bag ID: ").append(b.getBagID());
                    result.append(" | Marking: ").append(b.getFoodTypeMarking());
                    result.append(" | Location: ").append(b.getRackLocation());
                    result.append(" | Price: Rs.").append(b.getTotalPrice()).append("\n");
                    found = true;
                }
            }

            if (!found) {
                result.append("No active bags found for this customer.");
            }

            resultArea.setText(result.toString());
        });

        contentBox.getChildren().addAll(searchLabel, searchField, buttonBox, resultArea);
        root.setCenter(contentBox);

        Button backButton = createStyledButton("Back to Main Menu");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(10));
        root.setBottom(backButton);

        return new Scene(root);
    }

    private Scene createReportsScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Reports & Statistics");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web(PRIMARY_COLOR));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));

        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setMaxWidth(400);

        Button statsBtn = createMenuButton("View Storage Statistics");
        Button transBtn = createMenuButton("View Transaction History");
        Button floorBtn = createMenuButton("View Floor Details");

        buttonBox.getChildren().addAll(statsBtn, transBtn, floorBtn);

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(15);
        resultArea.setMaxWidth(600);

        statsBtn.setOnAction(e -> {
            int totalSlots = 0;
            int occupiedSlots = 0;
            double totalRevenue = 0;

            for (Floor floor : coldStorage.getFloors()) {
                for (Rack rack : floor.getRacks()) {
                    totalSlots += rack.getTotalSlots();
                    occupiedSlots += (rack.getTotalSlots() - rack.getEmptySlotCount());
                }
            }

            for (Transaction t : coldStorage.getTransactions()) {
                if (t.getType().equals("RETRIEVAL")) {
                    totalRevenue += t.getAmount();
                }
            }

            int activeBags = 0;
            for (Bag b : coldStorage.getAllBags()) {
                if (b.getStatus().equals("STORED")) activeBags++;
            }

            resultArea.setText("===== STORAGE STATISTICS =====\n\n" + "Total Slots: " + totalSlots + "\n" + "Occupied Slots: " + occupiedSlots + "\n" + "Empty Slots: " + (totalSlots - occupiedSlots) + "\n" + "Occupancy Rate: " + String.format("%.2f", (occupiedSlots * 100.0 / totalSlots)) + "%\n\n" + "Total Customers: " + coldStorage.getCustomers().size() + "\n" + "Total Bags Stored: " + coldStorage.getAllBags().size() + "\n" + "Active Bags: " + activeBags + "\n\n" + "Total Revenue: Rs." + totalRevenue + "\n" + "==============================");
        });

        transBtn.setOnAction(e -> {
            StringBuilder result = new StringBuilder("===== TRANSACTION HISTORY =====\n\n");

            if (coldStorage.getTransactions().isEmpty()) {
                result.append("No transactions yet.");
            } else {
                for (int i = coldStorage.getTransactions().size() - 1; i >= 0; i--) {
                    Transaction t = coldStorage.getTransactions().get(i);
                    result.append("ID: ").append(t.getTransactionID());
                    result.append(" | Type: ").append(t.getType());
                    result.append(" | Bag: ").append(t.getBagID());
                    result.append(" | Date: ").append(t.getDate());
                    result.append(" | Amount: Rs.").append(t.getAmount()).append("\n");
                }
            }

            result.append("\n===============================");
            resultArea.setText(result.toString());
        });

        floorBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Floor Details");
            dialog.setHeaderText("Enter Floor Number (1-3):");
            String input = dialog.showAndWait().orElse(null);

            if (input != null) {
                try {
                    int floorNum = Integer.parseInt(input);
                    if (floorNum < 1 || floorNum > 3) {
                        resultArea.setText("ERROR: Invalid floor number! Please enter 1, 2, or 3.");
                        return;
                    }

                    Floor floor = coldStorage.getFloors()[floorNum - 1];
                    StringBuilder result = new StringBuilder("===== FLOOR " + floorNum + " DETAILS =====\n\n");

                    for (Rack rack : floor.getRacks()) {
                        result.append("Rack ID: ").append(rack.getRackID()).append("\n");
                        result.append("  Empty Slots: ").append(rack.getEmptySlotCount());
                        result.append("/").append(rack.getTotalSlots()).append("\n");
                        result.append("  Current Weight: ").append(rack.getCurrentWeight());
                        result.append("/").append(rack.getMaxWeightCapacity()).append(" kg\n\n");
                    }

                    result.append("==============================");
                    resultArea.setText(result.toString());
                } catch (NumberFormatException ex) {
                    resultArea.setText("ERROR: Invalid floor number!");
                }
            }
        });

        contentBox.getChildren().addAll(buttonBox, resultArea);
        root.setCenter(contentBox);

        Button backButton = createStyledButton("Back to Main Menu");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(10));
        root.setBottom(backButton);

        return new Scene(root);
    }

    private void showMachineControl() {
        Stage dialog = new Stage();
        dialog.setTitle("Machine Room Control");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Machine Status");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web(PRIMARY_COLOR));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));

        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setMaxWidth(300);

        Button machine1Btn = createMenuButton("Toggle Machine 1");
        Button machine2Btn = createMenuButton("Toggle Machine 2");
        Button machine3Btn = createMenuButton("Toggle Machine 3");
        Button statusBtn = createMenuButton("View Status");

        buttonBox.getChildren().addAll(machine1Btn, machine2Btn, machine3Btn, statusBtn);

        TextArea statusArea = new TextArea();
        statusArea.setEditable(false);
        statusArea.setPrefRowCount(8);
        statusArea.setMaxWidth(400);

        machine1Btn.setOnAction(e -> {
            coldStorage.toggleMachine(1);
            updateMachineStatus(statusArea);
        });

        machine2Btn.setOnAction(e -> {
            coldStorage.toggleMachine(2);
            updateMachineStatus(statusArea);
        });

        machine3Btn.setOnAction(e -> {
            coldStorage.toggleMachine(3);
            updateMachineStatus(statusArea);
        });

        statusBtn.setOnAction(e -> updateMachineStatus(statusArea));

        contentBox.getChildren().addAll(buttonBox, statusArea);
        root.setCenter(contentBox);

        Button closeBtn = createStyledButton("Close");
        closeBtn.setOnAction(e -> dialog.close());
        BorderPane.setAlignment(closeBtn, Pos.CENTER);
        BorderPane.setMargin(closeBtn, new Insets(10));
        root.setBottom(closeBtn);

        updateMachineStatus(statusArea);

        Scene scene = new Scene(root, 500, 450);
        dialog.setScene(scene);
        dialog.show();
    }

    private void updateMachineStatus(TextArea statusArea) {
        StringBuilder status = new StringBuilder("===== MACHINE ROOM STATUS =====\n\n");

        for (MachineRoom m : coldStorage.getMachineRooms()) {
            status.append("Machine ID: ").append(m.getMachineID()).append("\n");
            status.append("Floor Number: ").append(m.getFloorNumber()).append("\n");
            status.append("Status: ").append(m.isOn() ? "ON" : "OFF").append("\n\n");
        }

        status.append("===============================");
        statusArea.setText(status.toString());
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        button.setStyle("-fx-background-color: " + PRIMARY_COLOR + "; -fx-text-fill: white;");
        button.setPrefWidth(200);
        button.setPrefHeight(40);
        return button;
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setStyle("-fx-background-color: white; -fx-text-fill: " + PRIMARY_COLOR + "; -fx-border-color: " + PRIMARY_COLOR + "; -fx-border-width: 2;");
        button.setPrefWidth(300);
        button.setPrefHeight(50);
        return button;
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}