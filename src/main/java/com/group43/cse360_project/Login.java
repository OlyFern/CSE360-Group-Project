package com.group43.cse360_project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    private Stage stage;

    public Login(Stage stage){
        this.stage = stage;
    }
    public Scene loginScene(){
        double sceneWidth = 1200;
        double sceneHeight = 675;

        BorderPane root = new BorderPane();

        root.setStyle("-fx-background-color: #F4F4D8;");

        /*
         ************************************************
         * Title
         ************************************************
         */
        Label text = new Label("Login");
        text.setStyle("-fx-font-size: 24px;");

        Image logoImage = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(200);

        VBox titleBox = new VBox(logoImageView, text);
        titleBox.setAlignment(Pos.CENTER);
        root.setTop(titleBox);

        /*
        ************************************************
        * This is the login fields
        ************************************************
         */
        // Username Field
        Label idLabel = new Label("ASU ID:");
        idLabel.setStyle("-fx-font-size: 16px;");
        TextField idField = new TextField();
        idField.setPromptText("Enter ID");

        // Password Field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-size: 16px;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        // CheckBox to show/hide password
        CheckBox showPasswordCheckbox = new CheckBox("Show Password");

        //same as password field but visible
        TextField visiblePassword = new TextField();
        visiblePassword.setPromptText("Enter password");
        visiblePassword.setManaged(false);
        visiblePassword.setVisible(false);

        //bind visibility to checkbox
        visiblePassword.managedProperty().bind(showPasswordCheckbox.selectedProperty());
        visiblePassword.visibleProperty().bind(showPasswordCheckbox.selectedProperty());

        passwordField.managedProperty().bind(showPasswordCheckbox.selectedProperty().not());
        passwordField.visibleProperty().bind(showPasswordCheckbox.selectedProperty().not());

        //match the text of password fields
        visiblePassword.textProperty().bindBidirectional(passwordField.textProperty());

        Label errorLabel = new Label("");

        Button confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-font-size: 16px;");
        confirmButton.setOnAction(e -> {
            String username = idField.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("All fields must be filled");
                errorLabel.setText("All fields must be filled");
            } else {
                try {
                    if (UserDB.validateLogin(username, password)) {
                        switchToPrototype();
                    }else {
                        System.out.println("Invalid username or password");
                        errorLabel.setText("Invalid username or password");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //grid of fields
        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.add(idLabel, 0, 0);
        inputGrid.add(idField, 1, 0);
        inputGrid.add(passwordLabel, 0, 1);
        inputGrid.add(passwordField, 1, 1);
        inputGrid.add(showPasswordCheckbox, 1, 2);
        inputGrid.add(visiblePassword, 1, 1);
        inputGrid.add(confirmButton, 1, 3);
        inputGrid.add(errorLabel, 1, 4);

        root.setCenter(inputGrid);

        return new Scene(root, sceneWidth, sceneHeight);
    }
    private void switchToPrototype(){
        PrototypeScene prototype = new PrototypeScene(stage);
        Scene scene = prototype.prototypeScene();
        stage.setScene(scene);
    }
}
