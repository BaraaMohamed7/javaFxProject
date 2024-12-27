package com.javafxproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuScene {
    Stage stage;

    Label mainHead;
    Button bookTicket;
    Button editBooking;
    Button cancelBooking;
    Button searchBooking;

    GridPane mainMenu;

    public MenuScene(Stage stage) {
        this.stage = stage;
        initControls();
        initActions();
    }

    public void initControls() {
        mainHead = new Label("Airway System");
        bookTicket = new Button("Book a ticket");
        editBooking = new Button("Edit Booking");
        cancelBooking = new Button("Cancel Booking");
        searchBooking = new Button("Search for Booking");
        mainMenu = new GridPane();

        mainMenu.add(mainHead, 1, 0);
        mainMenu.add(bookTicket, 1, 1);
        mainMenu.add(editBooking, 1, 2);
        mainMenu.add(cancelBooking, 1, 3);
        mainMenu.add(searchBooking, 1, 4);

        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setHgap(20);
        mainMenu.setVgap(20);

    }

    public void initActions() {
        searchBooking.setOnAction(e->{
            stage.setScene(new SearchScene(stage).getScene());
        });
        editBooking.setOnAction(e->{
            stage.setScene(new EditScene(stage).getScene());
        });
        bookTicket.setOnAction(e->{
            stage.setScene(new AddScene(stage).getScene());
        });
        cancelBooking.setOnAction(e->{
            stage.setScene(new DeleteScene(stage).getScene());
        });

    }

    public Scene getScene() {
        Scene mainMenuScene = new Scene(mainMenu, 400, 500);
        mainMenuScene.getStylesheets().add(App.class.getResource("main.css").toExternalForm());

        return mainMenuScene;
    }
}
