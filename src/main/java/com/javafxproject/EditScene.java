package com.javafxproject;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditScene {
    Stage stage;
    Label l1 = new Label("Choose id");
    Label l2 = new Label("Name");
    Label l3 = new Label("Email");
    Label l4 = new Label("Date");
    Label l5 = new Label("Nationality");
    Label l6 = new Label("Destination");
    Label l7 = new Label("Passport ID");
    TextField t1=new TextField();
    TextField t2=new TextField();
    TextField t3=new TextField();
    TextField t4=new TextField();
    TextField t5=new TextField();
    TextField t6=new TextField();
    Button b1= new Button("Edit");
    ComboBox<Integer> textIdComboBox = new ComboBox<>();
    GridPane g= new GridPane();
    Alert alert = new Alert(Alert.AlertType.ERROR,"Enter all values");


    EditScene(Stage stage) {
        this.stage = stage;
        initControls();
        initActions();
    }

    public void initControls() {
        textIdComboBox.setPrefWidth(200);
        textIdComboBox.setPlaceholder(new Label("ID"));
        try {
        textIdComboBox.setItems(DB.getTiIDs());
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.add(l1,0,0);
        g.add(textIdComboBox,0,1,2,1);
        g.add(l2,0,2);
        g.add(t1,0,3);
        g.add(l3,0,4);
        g.add(t2,0,5);
        g.add(l4,0,6);
        g.add(t3,0,7);
        g.add(l5,1,2);
        g.add(t4,1,3);
        g.add(l6,1,4);
        g.add(t5,1,5);
        g.add(l7,1,6);
        g.add(t6,1,7);
        g.add(b1,1,8);
        g.setAlignment(Pos.CENTER);
        g.setVgap(10);
        g.setHgap(10);
    }
    public void initActions() {
        b1.setOnAction((e) -> {
            if(t1.getText().isEmpty()||t2.getText().isEmpty()||t3.getText().isEmpty()||t4.getText().isEmpty()||t5.getText().isEmpty()||t6.getText().isEmpty()) {
            alert.show();
            return;
            }

            String name1 = t1.getText();
            String email1 = t2.getText();
            String date1 = t3.getText();
            String nat1 = t4.getText();
            String des1 = t5.getText();
            String pass1 = t6.getText();


            Connection c = DB.dbConnection();
            String sql = "UPDATE TICKETS SET NAME=?, NATIONALITY=?, EMAIL=?, DESTINATION=?, PASSPORTID=?, TARVELDATE=? WHERE id= ?";

            try {
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, name1);
                stmt.setString(2, nat1);
                stmt.setString(3, email1);
                stmt.setString(4, des1);
                stmt.setString(5, pass1);
                stmt.setString(6, date1);
                stmt.setString(7, textIdComboBox.getValue().toString());
                int i = stmt.executeUpdate();
                if(i==1){
                    Alert al = new Alert(Alert.AlertType.INFORMATION,"Data updated");
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
        Scene mainMenuScene = new Scene(g, 400, 500);
        mainMenuScene.getStylesheets().add(App.class.getResource("main.css").toExternalForm());

        return mainMenuScene;
    }


}

