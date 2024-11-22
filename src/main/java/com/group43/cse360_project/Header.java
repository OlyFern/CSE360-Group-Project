package com.group43.cse360_project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Header {
    /*******************************************************
     *Header: creates the header for buyer's and sellers
     * TODO: Only show options if a user has a specific account type. Low priority
     * TODO: Needs user information
     ******************************************************/

    public static HBox createHeader(Stage stage, User user) {
        //Background
        HBox header = new HBox(20);
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: #F4F4D8;");

        //logo
        Image logoImage = new Image(Header.class.getResourceAsStream("logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitHeight(100);
        logoImageView.setFitWidth(100);

        logoImageView.setOnMouseClicked(e -> switchToWelcome(stage));

        //buy button
        //TODO: Add functionality
        Button buyButton = new Button();
        Label buyLabel = new Label("Buy");
        buyLabel.setStyle("-fx-font-size: 18px");
        buyButton.setGraphic(buyLabel);
        buyButton.setMinWidth(100);

        //sell button
        //TODO: Add functionality
        Button sellButton = new Button();
        Label sellLabel = new Label("Sell");
        sellLabel.setStyle("-fx-font-size: 18px");
        sellButton.setGraphic(sellLabel);
        sellButton.setMinWidth(100);

        //checkout button
        //TODO: Add Checkout page
        Button cartButton = new Button();
        Label cartLabel = new Label("🛒");
        cartLabel.setStyle("-fx-font-size: 24px");
        cartButton.setGraphic(cartLabel);
        cartButton.setMinSize(50, 50);
        cartButton.setOnAction(e -> switchToCart(stage, user));

        //spacers
        Region spacer = new Region();
        spacer.setMinWidth(160);

        Region spacer2 = new Region();
        spacer2.setMinWidth(160);

        Region spacer3 = new Region();
        spacer3.setMinWidth(50);

        header.getChildren().addAll(logoImageView, spacer2, buyButton, spacer3, sellButton, spacer, cartButton);
        return header;
    }

    private static void switchToCart(Stage stage, User user) {
        Checkout cart = new Checkout(stage);
        Scene scene = cart.checkoutScene(user);
        stage.setScene(scene);
    }

    private static void switchToWelcome(Stage stage)  {
        Welcome welcome = new Welcome(stage);
        Scene scene = welcome.welcomeScene();
        stage.setScene(scene);
    }}
