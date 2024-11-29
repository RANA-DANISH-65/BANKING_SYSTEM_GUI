package com.example.bank_ease;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UTILITY_FUNCTIONS {
    public static Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #FFD700;");
        return label;
    }
    public static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 14px; -fx-background-color: #008CBA; -fx-text-fill: white; -fx-border-radius: 10; -fx-background-radius: 10;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #005f73; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white;"));
        button.setPrefWidth(200);
        return button;
    }
    public static ComboBox<String> createStyledComboBox(String[] items) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setStyle("-fx-font-size: 14px; -fx-border-color: #008CBA; -fx-border-radius: 5; -fx-background-radius: 5;");
        comboBox.setPrefWidth(200);
        return comboBox;
    }
    public static TextField createStyledTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-font-size: 14px; -fx-border-color: #008CBA; -fx-border-radius: 5; -fx-background-radius: 5;");
        textField.setPrefWidth(200);
        return textField;
    }
    public static RadioButton createStyledRadioButton(String text) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
        return radioButton;
    }
    public static void showResponseScene(String response) {
        Stage stage = new Stage();
        stage.setTitle("Response");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-background-color: black;");
        Label responseLabel = new Label(response);
        responseLabel.setStyle("-fx-font-size: 30px;  -fx-text-fill:red;");
        gridPane.getChildren().add(responseLabel);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();


    }
    public static Label createStyledTitle(String color, String title) {
        Label label = new Label(title);
        label.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill:" + color + ";");
        return label;
    }


}
