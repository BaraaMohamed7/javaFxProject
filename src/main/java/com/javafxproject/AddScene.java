package com.javafxproject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddScene {
    Stage stage;
    Label name = new Label("Name:");
    Label email = new Label("Email:");
    Label date = new Label("Date:");
    Label nat = new Label("Nationality:");
    Label des = new Label("Destination:");
    Label pass = new Label("PassPort ID:");
    TextField nameField = new TextField();
    TextField emailField = new TextField();
    TextField dateField = new TextField();
    TextField nationalityField = new TextField();
    TextField destinationField = new TextField();
    TextField passportField = new TextField();
    Button add = new Button("Add");
    Alert alert=new Alert(Alert.AlertType.ERROR,"Enter all value");
    GridPane gridPane = new GridPane();

    AddScene(Stage stage) {
        this.stage = stage;
        initControls();
        initActions();
    }

    public void initControls() {
        gridPane.add(name, 0, 0);
        gridPane.add(nameField, 1, 0);

        gridPane.add(email, 0, 1);
        gridPane.add(emailField, 1, 1);

        gridPane.add(date, 0, 2);
        gridPane.add(dateField, 1, 2);

        gridPane.add(nat, 0, 3);
        gridPane.add(nationalityField, 1, 3);

        gridPane.add(des, 0, 4);
        gridPane.add(destinationField, 1, 4);

        gridPane.add(pass, 0, 5);
        gridPane.add(passportField, 1, 5);
        gridPane.add(add,1,6);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
    }

    public void initActions() {
        add.setOnAction((e) -> {
        if(nameField.getText().isEmpty()||emailField.getText().isEmpty()||dateField.getText().isEmpty()||nationalityField.getText().isEmpty()||destinationField.getText().isEmpty()||passportField.getText().isEmpty()){
            alert.show();
            return;
        }
            String name1 = nameField.getText();
            String email1 = emailField.getText();
            String date1 = dateField.getText();
            String nat1 = nationalityField.getText();
            String des1 = destinationField.getText();
            String pass1 = passportField.getText();


            Connection c = DB.dbConnection();
            String sql = "insert into TICKETS (NAME, NATIONALITY, EMAIL, DESTINATION, PASSPORTID, TARVELDATE) values(?,?,?,?,?,?)";
            try {
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, name1);
                stmt.setString(2, nat1);
                stmt.setString(3, email1);
                stmt.setString(4, des1);
                stmt.setString(5, pass1);
                stmt.setString(6, date1);
                int i = stmt.executeUpdate();
                if(i==1){
                    Alert al = new Alert(Alert.AlertType.INFORMATION,"Data is Inserted");
                    al.showAndWait();
                    stage.setScene(new MenuScene(stage).getScene());
                }
                else{
                    Alert al = new Alert(Alert.AlertType.ERROR,"Invalid or missing data");
                    al.showAndWait();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });


    }

    public Scene getScene() {
        Scene mainMenuScene = new Scene(gridPane, 400, 500);
        mainMenuScene.getStylesheets().add(App.class.getResource("main.css").toExternalForm());

        return mainMenuScene;
    }
}