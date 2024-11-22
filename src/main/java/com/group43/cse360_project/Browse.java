package com.group43.cse360_project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.group43.cse360_project.Header.createHeader;

public class Browse {
    private Stage stage;

    public Browse(Stage stage) {
        this.stage = stage;
    }

    public Scene browseScene(int page) throws IOException {
        BorderPane root = new BorderPane();
        double sceneWidth = 1200;
        double sceneHeight = 675;

        HBox header = createHeader();
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
                    reloadBrowse(page-1);
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
                    reloadBrowse(page + 1);
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

        var reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        String line;
        for (int row = 0; row < 2; row++) {
            int col = 0;
            while (col < 7 && (line = reader.readLine()) != null) {
                String[] book = line.split(":");
                VBox bookEntry = createBookEntry(book);
                grid.add(bookEntry, col, row);
                col++;
            }
        }

        return grid;
    }

    private VBox createBookEntry(String[] book) {
        VBox entry = new VBox(5);
        entry.setAlignment(Pos.CENTER);

        Image coverImage = new Image(book[0]);
        ImageView coverImageView = new ImageView(coverImage);
        coverImageView.setFitWidth(100);
        coverImageView.setFitHeight(140);
        coverImageView.setOnMouseClicked(e -> switchToBook());

        Label priceLabel = new Label(book[1]);
        Label titleLabel = new Label(book[2] + ", " + book[3]);
        Label conditionLabel = new Label(book[4]);

        entry.getChildren().addAll(coverImageView, priceLabel, titleLabel, conditionLabel);
        return entry;
    }

    private void reloadBrowse(int page) throws IOException {
        Browse browse = new Browse(stage);
        Scene scene = browse.browseScene(page);
        stage.setScene(scene);
    }

    private void switchToBook(){
        BookPage book = new BookPage(stage);
        Scene scene = book.bookScene();
        stage.setScene(scene);
    }

}