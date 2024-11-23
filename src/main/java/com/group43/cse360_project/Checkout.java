package com.group43.cse360_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.LinkedList;


public class Checkout {
    private String conditions = "new";
    private Label subtotalLabel= new Label();
    private Label taxLabel= new Label();
    private Label totalLabel= new Label();
    private double taxrate = 0.056;

    private VBox listofItems = new VBox(50);


    private Stage stage;
    public Checkout(Stage stage) {
        this.stage = stage;
        stage.setTitle("Checkout");

    }

    public Scene checkoutScene(User user) {
        for (int i = 1; i <= 10; i++){
            int amount = (int) (Math.random() * 10) + 1;
            listofItems.getChildren().add(createList("NAME OF TITLE" + i, amount , conditions, 10));
        }
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F4F4D8;");
        /*
         * Texts
         */
        Label text = new Label("Checkout");
        text.setStyle("-fx-font-size: 50px; -fx-text-fill: #264252; -fx-font-weight: bold;");

        Label shoppingCart = new Label("Shopping Cart");
        shoppingCart.setStyle("-fx-font-size: 20px; -fx-text-fill: #264252; -fx-font-weight: bold;");

        Label paymentInfo = new Label("Payment Info");
        paymentInfo.setStyle("-fx-font-size: 20px; -fx-text-fill: #264252; -fx-font-weight: bold;");

        subtotalLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #264252");
        taxLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #264252");
        totalLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #264252");
        updateTotals();
        /*
         * Images
         */
        Image logoImage = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(120);
        logoImageView.setFitHeight(120);

        Image backArrow = new Image(getClass().getResourceAsStream("subdirectoryArrow.png"));
        ImageView backArrowImageView = new ImageView(backArrow);
        backArrowImageView.setFitWidth(30);
        backArrowImageView.setFitHeight(30);


        /*
         * Shapes
         */
        Rectangle pricingBox = new Rectangle(160, 300);
        pricingBox.setFill(Color.web("#F4F4D8"));
        pricingBox.setStroke(Color.web("#264252"));
        pricingBox.setStrokeWidth(3);

        Rectangle payment = new Rectangle(500, 450);
        payment.setFill(Color.web("#F4F4D8"));
        payment.setStroke(Color.web("#264252"));
        payment.setStrokeWidth(3);

        Rectangle cart = new Rectangle(500, 450);
        cart.setFill(Color.web("#F4F4D8"));
        cart.setStroke(Color.web("#264252"));
        cart.setStrokeWidth(3);
        /*
         * Buttons
         */
        Button backButton = new Button("Back", backArrowImageView);
        backButton.setStyle("-fx-font-size: 30px; -fx-background-color: #264252; -fx-text-fill: white;");
        backButton.setContentDisplay(ContentDisplay.RIGHT);
        backButton.setOnAction(e -> {
            try {
                changeScene(user);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button clearCart = new Button("Clear Cart");
        clearCart.setStyle("-fx-font-size: 10px; -fx-background-color: #E61610");
        clearCart.setOnAction(e -> clearList());

        Button orderButton = new Button("Place Order");
        orderButton.setStyle("-fx-font-size: 40px; -fx-text-fill: white; -fx-background-color: green");



        //The top of the screen
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.setSpacing(300);
        titleBox.getChildren().addAll(logoImageView, text, backButton);
        root.setTop(titleBox);




        VBox totals = new VBox(10);
        totals.getChildren().addAll(subtotalLabel, taxLabel, totalLabel);
        totals.setAlignment(Pos.CENTER);

        //StackPane PriceBox
        StackPane priceBox = new StackPane();
        priceBox.getChildren().addAll(pricingBox, totals);
        StackPane.setAlignment(totals, Pos.CENTER);


        /*
         *     Checkout Cart
         */
        //ScrollPane Checkout List
        ScrollPane scrollPane = new ScrollPane(listofItems);
        scrollPane.setPrefSize(480,450);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //HBox for the Cart
        HBox cartlabel = new HBox(275, shoppingCart, clearCart);

        //VBox to put into the stackpane Cart
        VBox checkoutCart = new VBox(cartlabel,scrollPane);
        checkoutCart.setAlignment(Pos.CENTER);
        checkoutCart.setPadding(new Insets(20));

        //StackPane Cart
        StackPane cartbox = new StackPane();
        cartbox.getChildren().addAll(cart,checkoutCart);

        /*
         * TextAREA
         */

        TextField email = new TextField();
        email.setPromptText("Enter Your Email");
        email.setMaxWidth(400);
        email.setPrefHeight(50);

        TextField cardNum = new TextField();
        //digits only
        cardNum.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));
        cardNum.setPromptText("Credit Card Number");
        cardNum.setMaxWidth(400);
        cardNum.setPrefHeight(50);

        TextField cardName = new TextField();
        cardName.setPromptText("Credit Card Holder's Name");
        cardName.setMaxWidth(400);
        cardName.setPrefHeight(50);

        TextField expDate = new TextField();
        //digits only
        expDate.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().matches("\\d*") & (change.getControlNewText().length() <= 4)){
                return change;
            }
            return null;
        }));
        expDate.setPromptText("Expiry Date");
        expDate.setPrefHeight(50);

        TextField CVV = new TextField();
        //digits only
        CVV.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().matches("\\d*") & (change.getControlNewText().length() <= 3)){
                return change;
            }
            return null;
        }));
        CVV.setPromptText("CVV");
        CVV.setPrefHeight(50);


        orderButton.setOnAction(e -> {
            String emailText = email.getText();
            String cardNumber = cardNum.getText();
            String holderName = cardName.getText();
            String expiryDate = expDate.getText();
            String CVVnumber = CVV.getText();
            System.out.println("Email: " + emailText);
            System.out.println("Credit Card Number: " + cardNumber);
            System.out.println("Credit Card Holder's Name: " + holderName);
            System.out.println("Expiry Date: " + expiryDate);
            System.out.println("CVV: " + CVVnumber);
            if (emailText.isEmpty() | cardNumber.isEmpty() | holderName.isEmpty() | expiryDate.isEmpty() | CVVnumber.isEmpty()){
                System.out.println("Missing an item!!!");
            }
            else orderItems();
        });

        // Layout for payment layout
        HBox creditCardInfo = new HBox(20, expDate, CVV);
        creditCardInfo.setAlignment(Pos.CENTER);
        VBox paylay = new VBox(20);
        paylay.getChildren().addAll(paymentInfo, email, cardNum, cardName, creditCardInfo, orderButton);
        paylay.setAlignment(Pos.CENTER);
        //StackPane
        StackPane paymentlayout = new StackPane();
        paymentlayout.getChildren().addAll(payment, paylay);
        paymentlayout.setAlignment(Pos.CENTER);
        //middle of the screen
        HBox center = new HBox();
        center.setAlignment(Pos.TOP_CENTER);
        center.setSpacing(5);
        center.getChildren().addAll(cartbox, priceBox, paymentlayout);
        root.setCenter(center);

        return new Scene(root, 1200, 600);
    }

    private void orderItems() {
        System.out.println("Price Paid: " + totalLabel.getText().substring(7));
        listofItems.getChildren().clear();
        updateTotals();
    }

    private void changeScene(User user) throws IOException {
        System.out.println("Back Button Pressed!");
        Browse browse = new Browse(stage);
        Scene scene = browse.browseScene(1, user, 0);
        stage.setScene(scene);
    }

    private HBox createList(String title, int quantity, String condition, double price) {
        Label titleLabel = new Label("Title\n" + title);
        Label quantityLabel = new Label("Quantity\n" + quantity);
        Label conditionLabel = new Label("Condition\n" + condition);
        Label priceLabel = new Label("Price\n $" + String.format("%.2f", price * quantity));

        titleLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #264252");
        quantityLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #264252");
        conditionLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #264252");
        priceLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #264252");


        Image delete = new Image(getClass().getResourceAsStream("delete.png"));
        ImageView deleteImageView = new ImageView(delete);
        deleteImageView.setFitWidth(15);
        deleteImageView.setFitHeight(15);

        Button deleteButton = new Button("Delete", deleteImageView);
        deleteButton.setOnAction(e -> {
            HBox parent = (HBox) deleteButton.getParent();
            Label quantityLabelin = (Label) parent.getChildren().get(1);

            int currentQuantity = Integer.parseInt(quantityLabelin.getText().split("\n")[1].trim());
            if (currentQuantity > 1) {
                currentQuantity--;
                quantityLabel.setText("Quantity\n" + currentQuantity);
                double updatePrice = price * currentQuantity;
                priceLabel.setText("Price\n $" + String.format("%.2f", updatePrice));
            }
            else{
                listofItems.getChildren().remove(deleteButton.getParent());
            }
            updateTotals();
        });

        HBox cartlayout = new HBox(20);
        cartlayout.setAlignment(Pos.CENTER_LEFT);
        cartlayout.getChildren().addAll(titleLabel, quantityLabel, conditionLabel, priceLabel, deleteButton);
        cartlayout.setUserData(price);
        return cartlayout;


    }

    private void updateTotals(){
        double subTotal = 0;
        for(Node item : listofItems.getChildren()) {
            if (item instanceof HBox itembox) {
                Label priceL = (Label) itembox.getChildren().get(3);
                String priceText = priceL.getText().split("\\$")[1].trim();
                double price = Double.parseDouble(priceText);
                subTotal += price;
            }
        }
        double tax = taxrate * subTotal;
        double total = subTotal + tax;

        subtotalLabel.setText("Subtotal: $" + String.format("%.2f", subTotal));
        taxLabel.setText("Tax: $" + String.format("%.2f", tax));
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    private void clearList() {
        listofItems.getChildren().clear();
        updateTotals();
    }
}


