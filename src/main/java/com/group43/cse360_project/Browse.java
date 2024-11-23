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
import java.util.Objects;

import static com.group43.cse360_project.BookDB.*;
import static com.group43.cse360_project.Header.createHeader;

public class Browse {
    private Stage stage;
    private User user;
    public Browse(Stage stage) {
        this.stage = stage;
    }

    public Scene browseScene(int page, User user, int x) throws IOException {
        int y = x;
        BorderPane root = new BorderPane();
        double sceneWidth = 1200;
        double sceneHeight = 675;
        this.user = user;

        HBox header = createHeader(stage, user);
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
        RadioButton naturalScience = new RadioButton("Natural Science");
        RadioButton computers = new RadioButton("Computers");
        RadioButton math = new RadioButton("Math");
        RadioButton english = new RadioButton("English Language");
        RadioButton other = new RadioButton("Other");


        Label conditionLabel = new Label("Condition");
        RadioButton likeNew = new RadioButton("Like New");
        RadioButton modUsed = new RadioButton("Moderately Used");
        RadioButton heavyUsed = new RadioButton("Heavily Used");

        switch (y){
            case 1: naturalScience.setSelected(true);
            break;
            case 2: computers.setSelected(true);
            break;
            case 3: math.setSelected(true);
            break;
            case 4: english.setSelected(true);
            break;
            case 5: other.setSelected(true);
            break;
            case 6: likeNew.setSelected(true);
            break;
            case 7: modUsed.setSelected(true);
            break;
            case 8: heavyUsed.setSelected(true);
            break;
        }
        ToggleGroup typeGroup = new ToggleGroup();
        naturalScience.setToggleGroup(typeGroup);
        computers.setToggleGroup(typeGroup);
        math.setToggleGroup(typeGroup);
        english.setToggleGroup(typeGroup);
        other.setToggleGroup(typeGroup);
        likeNew.setToggleGroup(typeGroup);
        modUsed.setToggleGroup(typeGroup);
        heavyUsed.setToggleGroup(typeGroup);



        filterPanel.getChildren().addAll(
                searchField, genreLabel,
                naturalScience, computers, math, english, other,
                conditionLabel, likeNew, modUsed, heavyUsed
        );

        root.setLeft(filterPanel);

        /*******************************************************
         * Book grid
         ******************************************************/

        GridPane bookGrid = createBookGrid(page, x);
        bookGrid.setStyle("-fx-background-color: white; -fx-border-color: darkgrey;");
        root.setCenter(bookGrid);
        typeGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedValue = getSelectedValue((RadioButton) newValue);
                try {
                    reloadBrowse(page, user, selectedValue);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

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
        int finalX = x;
        prevButton.setOnAction(e -> {
            if(page <= 1){
                return;
            }else{
                try {
                    reloadBrowse(page-1, user, finalX);
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
                    reloadBrowse(page + 1, user, finalX);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        root.setBottom(pagination);

        return new Scene(root, sceneWidth, sceneHeight);
    }

    private GridPane createBookGrid(int page, int x) throws IOException {
        /*******************************************************
         * GridPane
         * TODO: not create Book entry, access bookdb
         ******************************************************/
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));
        LinkedList<Book> books = getAllBooks();
        switch (x){
            case 1: books = getBooksByGenre(books, BookGenre.NATURAL_SCIENCE);
            break;
            case 2: books = getBooksByGenre(books, BookGenre.COMPUTERS);
            break;
            case 3: books = getBooksByGenre(books, BookGenre.MATH);
            break;
            case 4: books = getBooksByGenre(books, BookGenre.ENGLISH_LANGUAGE);
            break;
            case 5: books = getBooksByGenre(books, BookGenre.OTHER);
            break;
            case 6: books = getBooksByCondition(books, BookCondition.NEW);
            break;
            case 7: books = getBooksByCondition(books, BookCondition.USED);
            break;
            case 8: books = getBooksByCondition(books, BookCondition.HEAVY);
            break;

        }


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

    private int getSelectedValue(RadioButton selectedButton) {
        switch (selectedButton.getText()) {
            case "Natural Science":
                return 1;
            case "Computers":
                return 2;
            case "Math":
                return 3;
            case "English Language":
                return 4;
            case "Other":
                return 5;
            case "Like New":
                return 6;
            case "Moderately Used":
                return 7;
            case "Heavily Used":
                return 8;
            default:
                return 0;
        }
    }

    private VBox createBookEntry(Book book) {
        VBox entry = new VBox(5);
        entry.setAlignment(Pos.CENTER);

        //Rectangle coverImageView = new Rectangle(100, 140);
        System.out.println(book.getKey());
        Image coverImage = new Image(Objects.requireNonNull(Browse.class.getResourceAsStream(book.getKey())));
        ImageView coverImageView = new ImageView(coverImage);
        coverImageView.setFitWidth(100);
        coverImageView.setFitHeight(140);
        coverImageView.setOnMouseClicked(e -> {
            try {
                switchToBook(user, book);
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

    private void reloadBrowse(int page, User user, int x) throws IOException {
        Browse browse = new Browse(stage);
        Scene scene = browse.browseScene(page, user, x);
        stage.setScene(scene);
    }

    private void switchToBook(User user, Book bookListing) throws IOException {
        BookPage book = new BookPage(stage);
        Scene scene = book.bookScene(user, bookListing);
        stage.setScene(scene);
    }

}