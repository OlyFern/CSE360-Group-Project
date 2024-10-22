package com.group43.cse360_project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Welcome {
    private Stage stage;

    public Welcome(Stage stage){
        this.stage = stage;
    }

    public Scene welcomeScene(){
        double sceneWidth = 1200;
        double sceneHeight = 675;


        BorderPane root = new BorderPane();

        //create label Welcoming user
        Label welcome = new Label("Welcome to Knowledge Bank Books");
        welcome.setStyle("-fx-font-size: 24px;");

        //Logo
        Image logoImage = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(200);

        //Combine Logo and welcome and put in center top of the screen
        VBox welcomeBox = new VBox(logoImageView, welcome);
        welcomeBox.setAlignment(Pos.CENTER);
        root.setTop(welcomeBox);


        Button loginButton = new Button();
        Button signupButton = new Button();

        Label loginButtonLabel = new Label("Login");
        Label loginButtonSubLabel = new Label("Login by entering your\nASU ID and password");

        Label signupButtonLabel = new Label("Signup");
        Label signupButtonSubLabel = new Label("Create a buyer's or seller's\naccount using your ASU ID");

        loginButtonLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        loginButtonSubLabel.setStyle("-fx-font-size: 12px;");

        signupButtonLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        signupButtonSubLabel.setStyle("-fx-font-size: 12px;");

        VBox loginButtonBox = new VBox(loginButtonLabel, loginButtonSubLabel);
        loginButtonBox.setAlignment(Pos.CENTER);

        VBox signupButtonBox = new VBox(signupButtonLabel, signupButtonSubLabel);
        signupButtonBox.setAlignment(Pos.CENTER);

        loginButton.setGraphic(loginButtonBox);
        loginButton.setMinWidth(150);
        signupButton.setGraphic(signupButtonBox);
        signupButton.setMinWidth(150);

        loginButton.setOnAction(e -> switchToLogin());

        Region buffer = new Region();
        buffer.setMinWidth(100);
        HBox welcomeButtons = new HBox(loginButton, buffer, signupButton);

        welcomeButtons.setAlignment(Pos.CENTER);
        root.setCenter(welcomeButtons);

        root.setStyle("-fx-background-color: #F4F4D8;");
        return new Scene(root, sceneWidth, sceneHeight);
    }

    private void switchToLogin(){
        Login login = new Login(stage);
        Scene scene = login.loginScene();
        stage.setScene(scene);
    }
}
