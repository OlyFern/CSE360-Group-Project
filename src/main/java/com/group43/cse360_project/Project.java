package com.group43.cse360_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Project extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Knowledge Bank Books");

        Welcome welcome = new Welcome(stage);
        Scene scene = welcome.welcomeScene();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}