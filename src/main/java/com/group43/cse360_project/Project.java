package com.group43.cse360_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.group43.cse360_project.BookDB.addNewBook;

public class Project extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Knowledge Bank Books");

        //addNewBook("abcd", "Title", "Author", BookGenre.COMPUTERS, BookCondition.NEW, "seller", 9.99F, 12);

        Welcome welcome = new Welcome(stage);
        Scene scene = welcome.welcomeScene();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}