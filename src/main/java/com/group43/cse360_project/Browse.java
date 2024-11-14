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

import static com.group43.cse360_project.Header.createHeader;

public class Browse {
    private Stage stage;

    public Browse(Stage stage) {
        this.stage = stage;
    }

    public Scene browseScene() {
        BorderPane root = new BorderPane();
        double sceneWidth = 1200;
        double sceneHeight = 675;

        HBox header = createHeader();
        root.setTop(header);


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

        GridPane bookGrid = createBookGrid();
        bookGrid.setStyle("-fx-background-color: white; -fx-border-color: darkgrey;");
        root.setCenter(bookGrid);

        HBox pagination = new HBox(10);
        pagination.setAlignment(Pos.CENTER);
        pagination.setPadding(new Insets(10));

        Button prevButton = new Button("←");
        Button nextButton = new Button("→");
        pagination.getChildren().addAll(prevButton, nextButton);
        pagination.setStyle("-fx-background-color: lightgrey");


        root.setBottom(pagination);

        return new Scene(root, sceneWidth, sceneHeight);
    }

    private GridPane createBookGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 7; col++) {
                VBox bookEntry = createBookEntry();
                grid.add(bookEntry, col, row);
            }
        }

        return grid;
    }

    private VBox createBookEntry() {
        VBox entry = new VBox(5);
        entry.setAlignment(Pos.CENTER);

        Rectangle cover = new Rectangle(100, 140);
        cover.setStyle("-fx-fill: white; -fx-stroke: darkgrey;");
        cover.setOnMouseClicked(e -> switchToBook());

        Label priceLabel = new Label("Price");
        Label titleLabel = new Label("Title, Author");
        Label conditionLabel = new Label("Condition");

        entry.getChildren().addAll(cover, priceLabel, titleLabel, conditionLabel);
        return entry;
    }

    private void switchToBook(){
        Book book = new Book(stage);
        Scene scene = book.bookScene();
        stage.setScene(scene);
    }

}