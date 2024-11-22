package com.group43.cse360_project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import static com.group43.cse360_project.BookDB.getAllBooks;
import static com.group43.cse360_project.Header.createHeader;

public class Browse {
    private Stage stage;
    private User user;
    public Browse(Stage stage) {
        this.stage = stage;
    }

    public Scene browseScene(int page, User user) throws IOException {
        BorderPane root = new BorderPane();
        double sceneWidth = 1200;
        double sceneHeight = 675;
        this.user = user;

        HBox header = createHeader(stage);
        root.setTop(header);

        /*******************************************************
         * Filter Panel
         * TODO: Access Bookdb
         ******************************************************/
        VBox filterPanel = new VBox(10);
        filterPanel.setPadding(new Insets(10));
        filterPanel.setStyle("-fx-background-color: white; -fx-border-color: darkgrey;");

        TextField searchField = new TextField();
        searchField.setPromptText("Search");

        Label genreLabel = new Label("Genre:");
        CheckBox naturalScience = new CheckBox("Natural Science");
        CheckBox computers = new CheckBox("Computers");
        CheckBox math = new CheckBox("Math");
        CheckBox english = new CheckBox("English Language");
        CheckBox other = new CheckBox("Other");

        Label conditionLabel = new Label("Condition");
        CheckBox likeNew = new CheckBox("Like New");
        CheckBox modUsed = new CheckBox("Moderately Used");
        CheckBox heavyUsed = new CheckBox("Heavily Used");

        filterPanel.getChildren().addAll(
                searchField, genreLabel,
                naturalScience, computers, math, english, other,
                conditionLabel, likeNew, modUsed, heavyUsed
        );

        root.setLeft(filterPanel);

        /*******************************************************
         * Book grid
         ******************************************************/

        GridPane bookGrid = createBookGrid(page);
        bookGrid.setStyle("-fx-background-color: white; -fx-border-color: darkgrey;");
        root.setCenter(bookGrid);

        /*******************************************************
         * Pagination (the arrows to change page)
         ******************************************************/

        HBox pagination = new HBox(10);
        pagination.setAlignment(Pos.CENTER);
        pagination.setPadding(new Insets(10));

        Button prevButton = new Button("←");
        Button nextButton = new Button("→");
        pagination.getChildren().addAll(prevButton, nextButton);
        pagination.setStyle("-fx-background-color: lightgrey");
        prevButton.setOnAction(e -> {
            if(page <= 1){
                return;
            }else{
                try {
                    reloadBrowse(page-1, user);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        nextButton.setOnAction(e -> {
            if(bookGrid.getChildren().isEmpty()){
                return;
            }else{
                try {
                    reloadBrowse(page + 1, user);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        root.setBottom(pagination);

        return new Scene(root, sceneWidth, sceneHeight);
    }

    private GridPane createBookGrid(int page) throws IOException {
        /*******************************************************
         * GridPane
         * TODO: not create Book entry, access bookdb
         ******************************************************/
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));

        LinkedList<Book> books = getAllBooks();

        int i = 0;
        for (int row = 0; row < 2; row++) {
            int col = 0;
            while (col < 7  && i < books.size()) {
                VBox bookEntry = createBookEntry(books.get(i));
                grid.add(bookEntry, col, row);
                col++;
                i++;
            }
        }

        return grid;
    }

    private VBox createBookEntry(Book book) {
        VBox entry = new VBox(5);
        entry.setAlignment(Pos.CENTER);

        Rectangle coverImageView = new Rectangle(100, 140);
        //Image coverImage = new Image(book.getKey() + ".png");
        //ImageView coverImageView = new ImageView(coverImage);
        //coverImageView.setFitWidth(100);
       // coverImageView.setFitHeight(140);
        coverImageView.setOnMouseClicked(e -> {
            try {
                switchToBook(user);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Label priceLabel = new Label(Float.toString(book.getPrice()));
        Label titleLabel = new Label(book.getTitle() + ", " + book.getAuthor());
        Label conditionLabel = new Label();
        if(book.getCondition() == BookCondition.NEW) {
            conditionLabel.setText("New");
        } else if (book.getCondition() == BookCondition.USED) {
            conditionLabel.setText("Moderately Used");
        }else{
            conditionLabel.setText("Heavily Used");
        }


        entry.getChildren().addAll(coverImageView, priceLabel, titleLabel, conditionLabel);
        return entry;
    }

    private void reloadBrowse(int page, User user) throws IOException {
        Browse browse = new Browse(stage);
        Scene scene = browse.browseScene(page, user);
        stage.setScene(scene);
    }

    private void switchToBook(User user) throws IOException {
        BookPage book = new BookPage(stage);
        Scene scene = book.bookScene(user);
        stage.setScene(scene);
    }

}