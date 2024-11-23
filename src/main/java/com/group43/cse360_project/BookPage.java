package com.group43.cse360_project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.group43.cse360_project.BookGenre.bookGenreAsString;
import static com.group43.cse360_project.Header.createHeader;

public class BookPage {
    private Stage stage;

    public BookPage(Stage stage) {
        this.stage = stage;
    }

    public Scene bookScene(User user, Book book) {
        double sceneWidth = 1200;
        double sceneHeight = 675;

        //Header
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white;");

        HBox header = createHeader(stage, user);
        root.setTop(header);

        //Book
        //TODO: access Bookdb
        Rectangle cover = new Rectangle(330, 462);
        cover.setStyle("-fx-fill: white; -fx-stroke: darkgrey;");
        VBox coverContainer = new VBox(cover);
        coverContainer.setAlignment(Pos.CENTER);
        coverContainer.setPadding(new Insets(50));

        root.setLeft(coverContainer);

        /*******************************************************
         * List details of the book
         * TODO: Access Bookdb
         ******************************************************/
        Label titleLabel = new Label("Title:\t\t\t");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label title = new Label(book.getTitle());
        title.setStyle("-fx-font-size: 14px");
        HBox titleBox = new HBox(titleLabel, title);
        titleBox.setPadding(new Insets(10));

        Label authorLabel = new Label("Author:\t\t\t");
        authorLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label author = new Label(book.getAuthor());
        author.setStyle("-fx-font-size: 14px");
        HBox authorBox = new HBox(authorLabel, author);
        authorBox.setPadding(new Insets(10));

        Label genreLabel = new Label("Genre:\t\t\t");
        genreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label genre = new Label(bookGenreAsString(book.getGenre()));
        genre.setStyle("-fx-font-size: 14px");
        HBox genreBox = new HBox(genreLabel, genre);
        genreBox.setPadding(new Insets(10));

        Label conditionLabel = new Label("Condition:\t\t");
        conditionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label condition = new Label(book.getCondition().toString());
        condition.setStyle("-fx-font-size: 14px");
        HBox conditionBox = new HBox(conditionLabel, condition);
        conditionBox.setPadding(new Insets(10));

        Label quantityLabel = new Label("Quantity:\t\t\t");
        quantityLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label quantity = new Label(Integer.toString(book.getQuantity()));
        quantity.setStyle("-fx-font-size: 14px");
        HBox quantityBox = new HBox(quantityLabel, quantity);
        quantityBox.setPadding(new Insets(10));


        Label sellPriceLabel = new Label("Sell Price:\t\t");
        sellPriceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label sellPrice = new Label(Float.toString(book.getPrice()));
        sellPrice.setStyle("-fx-font-size: 14px");
        HBox sellPriceBox = new HBox(sellPriceLabel, sellPrice);
        sellPriceBox.setPadding(new Insets(10));

        //put all the details in a VBOX
        VBox bookDetails = new VBox(titleBox, authorBox, genreBox, conditionBox, quantityBox, sellPriceBox);
        bookDetails.setAlignment(Pos.CENTER);
        bookDetails.setPadding(new Insets(50));
        root.setCenter(bookDetails);

        //add to cart button
        Button checkoutButton = new Button();
        checkoutButton.setText("Add to Cart");
        VBox checkoutContainer = new VBox(checkoutButton);
        checkoutContainer.setAlignment(Pos.BOTTOM_RIGHT);
        checkoutContainer.setPadding(new Insets(80));
        checkoutButton.setOnAction(e -> {user.addToCart(book);});

        root.setRight(checkoutContainer);


        return new Scene(root, sceneWidth, sceneHeight);
    }
}
