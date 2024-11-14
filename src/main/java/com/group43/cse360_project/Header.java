package com.group43.cse360_project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class Header {
    public static HBox createHeader() {
        HBox header = new HBox(20);
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: #F4F4D8;");

        Image logoImage = new Image(Header.class.getResourceAsStream("logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitHeight(100);
        logoImageView.setFitWidth(100);


        Button buyButton = new Button("Buy");
        Button sellButton = new Button("Sell");

        Button cartButton = new Button("🛒");
        Region spacer = new Region();
        spacer.setMinWidth(300);

        Region spacer2 = new Region();
        spacer2.setMinWidth(250);

        header.getChildren().addAll(logoImageView, spacer2, buyButton, sellButton, spacer, cartButton);
        return header;
    }
}
