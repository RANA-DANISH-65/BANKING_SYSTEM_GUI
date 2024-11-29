package com.example.bank_ease;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Account;
import models.AccountStatus;
import models.AccountType;
import models.Manager;

public class GUI_OPERATION_HANDLER {
    public static void showAddAccountScene(Manager manager) {
        Stage stage = new Stage();
        Label title = new Label("Add New Account Menu");
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #FFD700;");
        Label accountIDLabel = UTILITY_FUNCTIONS.createStyledLabel("Account ID:");
        TextField accountIDTextField = UTILITY_FUNCTIONS.createStyledTextField("Enter Account ID...");
        Label accountNameLabel = UTILITY_FUNCTIONS.createStyledLabel("Account Holder Name:");
        TextField accountNameField = UTILITY_FUNCTIONS.createStyledTextField("Enter Account Name...");
        Label accountTypeLabel = UTILITY_FUNCTIONS.createStyledLabel("Select Account Type:");
        String[] accountTypes = {"SAVING", "CHECKING", "BUSINESS"};
        ComboBox<String> accountTypeComboBox = UTILITY_FUNCTIONS.createStyledComboBox(accountTypes);
        Label accountStatusLabel = UTILITY_FUNCTIONS.createStyledLabel("Account Status:");
        RadioButton activeRadioButton = UTILITY_FUNCTIONS.createStyledRadioButton("ACTIVE");
        RadioButton inActiveRadioButton = UTILITY_FUNCTIONS.createStyledRadioButton("INACTIVE");
        ToggleGroup accountStatusGroup = new ToggleGroup();
        activeRadioButton.setToggleGroup(accountStatusGroup);
        inActiveRadioButton.setToggleGroup(accountStatusGroup);
        Label accountBalanceLabel = UTILITY_FUNCTIONS.createStyledLabel("Account Balance:");
        TextField accountBalanceField = UTILITY_FUNCTIONS.createStyledTextField("Enter Balance");
        Button addButton = UTILITY_FUNCTIONS.createStyledButton("Add Account");
        Label Response = new Label();
        Response.setStyle("-fx-font-size: 30px;  -fx-text-fill:red;");
        addButton.setOnAction(e -> {
            Response.setText("");
            String accountID = accountIDTextField.getText().trim();
            String accountName = accountNameField.getText().trim();
            String selectedType = accountTypeComboBox.getValue();
            if (selectedType == null) {
                Response.setText("No Account Type Selected");
                return;
            }
            Toggle selectedToggle = accountStatusGroup.getSelectedToggle();
            if (selectedToggle == null) {
                Response.setText("No account status selected!");
                return;
            }
            String statusText = ((RadioButton) selectedToggle).getText();
            double balance;
            try {
                balance = Double.parseDouble(accountBalanceField.getText());
            } catch (NumberFormatException ex) {
                Response.setText("Invalid balance input!");
                return;
            }
            if (accountID.isEmpty() || accountName.isEmpty() || selectedType == null || statusText == null || balance < 0) {
                Response.setText("Please fill all fields correctly");
                return;
            }
            manager.addAccount(accountID, accountName, selectedType, statusText, balance);
            UTILITY_FUNCTIONS.showResponseScene("Account Added Successfully for " + accountName);
            stage.close();
        });
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-background-color: black;");
        gridPane.add(title, 0, 0, 2, 1);
        gridPane.add(accountIDLabel, 0, 1);
        gridPane.add(accountIDTextField, 1, 1);
        gridPane.add(accountNameLabel, 0, 2);
        gridPane.add(accountNameField, 1, 2);
        gridPane.add(accountTypeLabel, 0, 3);
        gridPane.add(accountTypeComboBox, 1, 3);
        gridPane.add(accountStatusLabel, 0, 4);
        gridPane.add(activeRadioButton, 1, 4);
        gridPane.add(inActiveRadioButton, 1, 5);
        gridPane.add(accountBalanceLabel, 0, 6);
        gridPane.add(accountBalanceField, 1, 6);
        gridPane.add(addButton, 0, 7, 2, 1);
        GridPane.setHalignment(addButton, javafx.geometry.HPos.CENTER);
        gridPane.add(Response, 0, 8, 2, 1);
        GridPane.setHalignment(addButton, javafx.geometry.HPos.CENTER);
        Scene scene = new Scene(gridPane, 600, 500);
        stage.setTitle("Add New Account");
        stage.setScene(scene);
        stage.show();
    }
    public static void showRemoveAccountScene(Manager manager) {
        Stage stage = new Stage();
        Label title = UTILITY_FUNCTIONS.createStyledTitle("#FF6347", "Remove Account Menu");
        Label accountID = UTILITY_FUNCTIONS.createStyledLabel("Account ID");
        TextField accountIDField = UTILITY_FUNCTIONS.createStyledTextField("Enter Account ID to remove...");
        Button removeButton = new Button("Remove Account");
        Label response = UTILITY_FUNCTIONS.createStyledTitle("tomato", "");
        removeButton.setStyle("-fx-background-color: #3A4A69; -fx-text-fill: white; -fx-font-size: 16px;");
        removeButton.setOnAction(e -> {
            String removeID = accountIDField.getText();
            if (removeID.equals(null)) {
                response.setText("Please Fill Field Correctly");
                return;
            }
            boolean responseFromBackend = manager.removeAccount(removeID);
            if (responseFromBackend) {
                stage.close();
                UTILITY_FUNCTIONS.showResponseScene("Account Removed Successfully for ID " + removeID);
            } else {
                UTILITY_FUNCTIONS.showResponseScene("Account Not Found for ID ");
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-background-color: #2E3B4E;");
        gridPane.add(title, 0, 0, 2, 1);
        gridPane.add(accountID, 0, 1);
        gridPane.add(accountIDField, 1, 1);
        gridPane.add(removeButton, 0, 3, 2, 1);
        GridPane.setHalignment(removeButton, javafx.geometry.HPos.CENTER);
        stage.setTitle("Remove Account");
        Scene scene = new Scene(gridPane, 600, 300);
        stage.setScene(scene);
        stage.show();
    }
    public static void showSearchAccountScene(Manager manager) {
        Stage stage = new Stage();
        stage.setTitle("Search Account");
        Label title = UTILITY_FUNCTIONS.createStyledTitle("#FFD700", "Search Account");
        Label accountID = UTILITY_FUNCTIONS.createStyledLabel("Account ID");
        TextField accountIDField = UTILITY_FUNCTIONS.createStyledTextField("Enter Account ID to search...");
        Button searchButton = new Button("Search Account");
        Label response = UTILITY_FUNCTIONS.createStyledTitle("tomato", "");
        searchButton.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-size: 16px;");
        searchButton.setOnAction(e -> {
            String accountId = accountIDField.getText();
            if (accountId.equals(null)) {
                response.setText("Please Enter Account Id");
                return;
            } else {
                Account account = manager.searchAccount(accountId);
                if (account != null) {

                    StringBuilder accountDetails = new StringBuilder();
                    accountDetails.append("Account Holder Name: ").append(account.getAccountHolderName()).append("\n")
                            .append("Account ID: ").append(account.getAccountID()).append("\n")
                            .append("Account Type: ").append(account.getAccountType()).append("\n")
                            .append("Account Status: ").append(account.getAccountStatus()).append("\n")
                            .append("Balance: ").append(String.format("%.2f", account.getAccountBalance())).append("\n");
                    stage.close();
                    UTILITY_FUNCTIONS.showResponseScene(accountDetails.toString());
                } else {
                    UTILITY_FUNCTIONS.showResponseScene("Account Not Found for ID " + accountId);
                }
            }

        });
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-background-color: #2E3B4E;");
        gridPane.add(title, 0, 0, 2, 1);
        gridPane.add(accountID, 0, 1);
        gridPane.add(accountIDField, 1, 1);
        gridPane.add(searchButton, 0, 3, 2, 1);
        GridPane.setHalignment(searchButton, javafx.geometry.HPos.CENTER);
        gridPane.add(response, 0, 4, 2, 1);
        Scene scene = new Scene(gridPane, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
    public static void showCloseAccountScene(Manager manager) {
        Stage stage = new Stage();
        stage.setTitle("Close Account");
        Label title = UTILITY_FUNCTIONS.createStyledTitle("#FFD700", "Close Account");
        Label accountID = UTILITY_FUNCTIONS.createStyledLabel("Account ID");
        TextField accountIDField = UTILITY_FUNCTIONS.createStyledTextField("Enter Account ID to close...");
        Button searchButton = new Button("Close Account");
        Label response = UTILITY_FUNCTIONS.createStyledTitle("tomato", "");
        searchButton.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-size: 16px;");
        searchButton.setOnAction(e -> {
            String accountId = accountIDField.getText();
            if (accountId.equals(null)) {
                response.setText("Please Enter Account Id");
                return;
            } else {
                Account account = manager.searchAccount(accountId);
                if (account != null) {
                    manager.closeAccount(account);
                    stage.close();
                    UTILITY_FUNCTIONS.showResponseScene("Account Closed Successfully for " + account.getAccountHolderName());

                } else {
                    UTILITY_FUNCTIONS.showResponseScene("Account Not Found for ID " + accountId);
                }
            }

        });
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-background-color: #2E3B4E;");
        gridPane.add(title, 0, 0, 2, 1);
        gridPane.add(accountID, 0, 1);
        gridPane.add(accountIDField, 1, 1);
        gridPane.add(searchButton, 0, 3, 2, 1);
        GridPane.setHalignment(searchButton, javafx.geometry.HPos.CENTER);
        gridPane.add(response, 0, 4, 2, 1);
        Scene scene = new Scene(gridPane, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
    public static void showSummaryScene(Manager manager) {
        String[] summary = manager.getSummary();
        StringBuilder summaryString = new StringBuilder();
        summaryString.append("Total Accounts: ").append(summary[0]).append("\n")
                .append("Total Active Accounts: ").append(summary[1]).append("\n")
                .append("Total Business Accounts: ").append(summary[2]).append("\n")
                .append("Total Saving Accounts: ").append(summary[3]).append("\n")
                .append("Total Checking Accounts: ").append(summary[4]).append("\n")
                .append(" Total Balance: ").append(summary[5]).append("\n");
        UTILITY_FUNCTIONS.showResponseScene(summaryString.toString());

    }
    public static void showUpdateAccountScene(Manager manager){
        Stage stage = new Stage();
        stage.setTitle("Update Account");
        Label title = UTILITY_FUNCTIONS.createStyledTitle("#FFD700", "Update Account");
        Label accountID = UTILITY_FUNCTIONS.createStyledLabel("Account ID");
        TextField accountIDField = UTILITY_FUNCTIONS.createStyledTextField("Enter Account ID to update...");
        Button searchButton = new Button("Update Account");
        Label response = UTILITY_FUNCTIONS.createStyledTitle("tomato", "");
        searchButton.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-size: 16px;");
        searchButton.setOnAction(e -> {
            String accountId = accountIDField.getText();
            if (accountId.equals(null)) {
                response.setText("Please Enter Account Id");
                return;
            } else {
                Account account = manager.searchAccount(accountId);
                if (account != null) {
                     updateAccount(account,manager);
                } else {
                    UTILITY_FUNCTIONS.showResponseScene("Account Not Found for ID " + accountId);
                }
            }

        });
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-background-color: #2E3B4E;");
        gridPane.add(title, 0, 0, 2, 1);
        gridPane.add(accountID, 0, 1);
        gridPane.add(accountIDField, 1, 1);
        gridPane.add(searchButton, 0, 3, 2, 1);
        GridPane.setHalignment(searchButton, javafx.geometry.HPos.CENTER);
        gridPane.add(response, 0, 4, 2, 1);
        Scene scene = new Scene(gridPane, 600, 400);
        stage.setScene(scene);
        stage.show();

    }
    public static void updateAccount(Account account,Manager manager){
        Stage stage = new Stage();
        Label title = new Label("Update  Account Menu");
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #FFD700;");
        Label accountIDLabel = UTILITY_FUNCTIONS.createStyledLabel("Account ID:");
        TextField accountIDTextField = UTILITY_FUNCTIONS.createStyledTextField("Enter Account ID...");
        accountIDTextField.setText(account.getAccountID());
        Label accountNameLabel = UTILITY_FUNCTIONS.createStyledLabel("Account Holder Name:");
        TextField accountNameField = UTILITY_FUNCTIONS.createStyledTextField("Enter Account Name...");
        accountNameField.setText(account.getAccountHolderName());
        Label accountTypeLabel = UTILITY_FUNCTIONS.createStyledLabel("Select Account Type:");
        String[] accountTypes = {"SAVING", "CHECKING", "BUSINESS"};
        ComboBox<String> accountTypeComboBox = UTILITY_FUNCTIONS.createStyledComboBox(accountTypes);
        accountTypeComboBox.setValue(account.getAccountType().name());
        Label accountStatusLabel = UTILITY_FUNCTIONS.createStyledLabel("Account Status:");
        RadioButton activeRadioButton = UTILITY_FUNCTIONS.createStyledRadioButton("ACTIVE");
        RadioButton inActiveRadioButton = UTILITY_FUNCTIONS.createStyledRadioButton("INACTIVE");
        ToggleGroup accountStatusGroup = new ToggleGroup();
        activeRadioButton.setToggleGroup(accountStatusGroup);
        inActiveRadioButton.setToggleGroup(accountStatusGroup);
        Label accountBalanceLabel = UTILITY_FUNCTIONS.createStyledLabel("Account Balance:");
        TextField accountBalanceField = UTILITY_FUNCTIONS.createStyledTextField("Enter Balance");
        accountBalanceField.setText(String.valueOf(account.getAccountBalance()));
        Button addButton = UTILITY_FUNCTIONS.createStyledButton("Update Account");
        Label Response = new Label();
        Response.setStyle("-fx-font-size: 30px;  -fx-text-fill:red;");
        addButton.setOnAction(e -> {
            Response.setText("");
            String accountID = accountIDTextField.getText().trim();
            String accountName = accountNameField.getText().trim();
            String selectedType = accountTypeComboBox.getValue();
            if (selectedType == null) {
                Response.setText("No Account Type Selected");
                return;
            }
            Toggle selectedToggle = accountStatusGroup.getSelectedToggle();
            if (selectedToggle == null) {
                Response.setText("No account status selected!");
                return;
            }
            String statusText = ((RadioButton) selectedToggle).getText();
            double balance;
            try {
                balance = Double.parseDouble(accountBalanceField.getText());
            } catch (NumberFormatException ex) {
                Response.setText("Invalid balance input!");
                return;
            }
            if (accountID.isEmpty() || accountName.isEmpty() || selectedType == null || statusText == null || balance < 0) {
                Response.setText("Please fill all fields correctly");
                return;
            }
            boolean updated=manager.updateAccount(accountID, accountName,balance,AccountStatus.valueOf(statusText), AccountType.valueOf(selectedType));
            if(updated){
                UTILITY_FUNCTIONS.showResponseScene("Account Updated Successfully");
            }else{
                UTILITY_FUNCTIONS.showResponseScene("Account not Found");
            }
            stage.close();
        });
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-background-color: black;");
        gridPane.add(title, 0, 0, 2, 1);
        gridPane.add(accountIDLabel, 0, 1);
        gridPane.add(accountIDTextField, 1, 1);
        gridPane.add(accountNameLabel, 0, 2);
        gridPane.add(accountNameField, 1, 2);
        gridPane.add(accountTypeLabel, 0, 3);
        gridPane.add(accountTypeComboBox, 1, 3);
        gridPane.add(accountStatusLabel, 0, 4);
        gridPane.add(activeRadioButton, 1, 4);
        gridPane.add(inActiveRadioButton, 1, 5);
        gridPane.add(accountBalanceLabel, 0, 6);
        gridPane.add(accountBalanceField, 1, 6);
        gridPane.add(addButton, 0, 7, 2, 1);
        GridPane.setHalignment(addButton, javafx.geometry.HPos.CENTER);
        gridPane.add(Response, 0, 8, 2, 1);
        GridPane.setHalignment(addButton, javafx.geometry.HPos.CENTER);
        Scene scene = new Scene(gridPane, 600, 500);
        stage.setTitle("Add New Account");
        stage.setScene(scene);
        stage.show();
    }
}
