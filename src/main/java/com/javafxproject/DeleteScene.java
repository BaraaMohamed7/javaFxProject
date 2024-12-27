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

public class DeleteScene {
    Stage stage;
    Label delete=new Label("Delete Reservation");
    Label pass = new Label("ID:");
    TextField idField = new TextField();
    Button bdelete= new Button("Delete");
    GridPane root=new GridPane();

    DeleteScene(Stage stage) {
        this.stage=stage;
        initControls();
        initActions();
    }
    public void initControls(){
        root.add(delete,0,0,3,1);
        root.add(pass,0,1);
        root.add(idField,1,1);
        root.add(bdelete,1,2);
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
    }
    public void initActions() {
        bdelete.setOnAction(e -> {
            Connection c = DB.dbConnection();
            String sql= "DELETE FROM TICKETS WHERE ID=?";
            try {
                PreparedStatement pst = c.prepareStatement(sql);
                pst.setString(1,idField.getText());
                int res = pst.executeUpdate();
                if (res == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Success");
                    alert.showAndWait();
                    stage.setScene(new MenuScene(stage).getScene());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public Scene getScene() {
        Scene mainMenuScene = new Scene(root, 400, 500);
        mainMenuScene.getStylesheets().add(App.class.getResource("main.css").toExternalForm());

        return mainMenuScene;
    }
}
