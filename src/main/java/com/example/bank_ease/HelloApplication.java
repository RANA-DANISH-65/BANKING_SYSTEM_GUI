package com.example.bank_ease;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Manager;

import javax.swing.*;

public class HelloApplication extends Application {
    private Manager manager;

    public HelloApplication() {
        this.manager = new Manager();
    }

    @Override
    public void start(Stage primaryStage) {
        Label title = new Label("  MANAGER MENU");
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: white;");
        Button addAccountButton = createStyledButton("Add Account");
        Button removeAccountButton = createStyledButton("Remove Account");
        Button searchAccountButton = createStyledButton("Search Account");
        Button closeAccountButton = createStyledButton("Close Account");
        Button updateAccountButton = createStyledButton("Update Account");
        Button getSummaryButton = createStyledButton("Get Summary");
        Button withdrawButton = createStyledButton("Withdraw");
        Button depositButton = createStyledButton("Deposit");
        Button exitButton = createStyledButton("Exit");
        exitButton.setOnAction(e -> primaryStage.close());
        addAccountButton.setOnAction(e -> GUI_OPERATION_HANDLER.showAddAccountScene(manager));
        removeAccountButton.setOnAction(e -> GUI_OPERATION_HANDLER.showRemoveAccountScene(manager));
        searchAccountButton.setOnAction(e -> GUI_OPERATION_HANDLER.showSearchAccountScene(manager));
        closeAccountButton.setOnAction(e -> GUI_OPERATION_HANDLER.showCloseAccountScene(manager));
        updateAccountButton.setOnAction(e -> GUI_OPERATION_HANDLER.showUpdateAccountScene(manager));
        getSummaryButton.setOnAction(e -> GUI_OPERATION_HANDLER.showSummaryScene(manager));
        withdrawButton.setOnAction(e -> showWithdrawScene());
        depositButton.setOnAction(e -> showDepositScene());
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-background-color: black;");
        gridPane.add(title, 0, 0, 2, 1);
        gridPane.add(addAccountButton, 0, 1);
        gridPane.add(removeAccountButton, 1, 1);
        gridPane.add(searchAccountButton, 0, 2);
        gridPane.add(closeAccountButton, 1, 2);
        gridPane.add(updateAccountButton, 0, 3);
        gridPane.add(getSummaryButton, 1, 3);
        gridPane.add(withdrawButton, 0, 4);
        gridPane.add(depositButton, 1, 4);
        gridPane.add(exitButton, 0, 5, 2, 1);
        GridPane.setHalignment(exitButton, javafx.geometry.HPos.CENTER);
        Scene scene = new Scene(gridPane, 600, 500);
        primaryStage.setTitle("Manager Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 14px; -fx-background-color: #008CBA; -fx-text-fill: white; -fx-border-radius: 10; -fx-background-radius: 10;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #005f73; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white;"));
        button.setPrefWidth(200);
        return button;
    }




    private void showRemoveAccountScene() {
        System.out.println("Show Remove Account Form");
    }
    private void showSearchAccountScene() {
        System.out.println("Show Search Account Form");
    }
    private void showCloseAccountScene() {
        System.out.println("Show Close Account Form");
    }
    private void showUpdateAccountScene() {
        System.out.println("Show Update Account Form");
    }
    private void showSummaryScene() {
        System.out.println("Show Account Summary");
    }
    private void showWithdrawScene() {
        System.out.println("Show Withdraw Form");
    }
    private void showDepositScene() {
        System.out.println("Show Deposit Form");
    }

    public static void main(String[] args) {
        launch();
    }
}
