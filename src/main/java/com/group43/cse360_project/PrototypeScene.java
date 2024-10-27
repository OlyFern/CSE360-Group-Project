package com.group43.cse360_project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrototypeScene {
    private Stage stage;

    public PrototypeScene(Stage stage){
        this.stage = stage;
    }
    public Scene prototypeScene(){
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
         * Victory Screen
         ************************************************
         */
        // Username Field
        Label victory = new Label("Login Successful");
        victory.setStyle("-fx-font-size: 32px;");

        root.setCenter(victory);

        return new Scene(root, sceneWidth, sceneHeight);
    }
}
