package com.javafxproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Scene signUpScene = new SignUpScene(stage).getScene();
        signUpScene.getStylesheets().add(App.class.getResource("main.css").toExternalForm());

        stage.setTitle("Airway System!");
        stage.setScene(signUpScene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}