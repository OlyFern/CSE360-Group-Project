package com.group43.cse360_project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Signup {
    private Stage stage;

    public Signup(Stage stage){
        this.stage = stage;
    }
    public Scene signupScene(){
        double sceneWidth = 1200;
        double sceneHeight = 675;

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F4F4D8;");


        /*
         ************************************************
         * Title
         ************************************************
         */
        Label text = new Label("Signup");
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
         * Signup field
         ************************************************
         */
        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-font-size: 16px;");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        // Password Field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-size: 16px;");
        TextField passwordField = new TextField();
        passwordField.setPromptText("Enter password");

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setStyle("-fx-font-size: 16px;");
        TextField confirmPasswordField = new TextField();
        confirmPasswordField.setPromptText("Retype password");

        // Grid of signup fields
        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.add(usernameLabel, 0, 0);
        inputGrid.add(usernameField, 1, 0);
        inputGrid.add(passwordLabel, 0, 1);
        inputGrid.add(passwordField, 1, 1);
        inputGrid.add(confirmPasswordLabel, 0, 2);
        inputGrid.add(confirmPasswordField, 1, 2);

        root.setCenter(inputGrid);

        return new Scene(root, sceneWidth, sceneHeight);
    }
}
