package com.javafxproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignInScene {
    Stage stage;
    Label head = new Label("Airway System");
    Label email = new Label("Email:");
    TextField textFieldForEmail = new TextField();
    Label password = new Label("Password:");
    PasswordField textFieldForPassword = new PasswordField();
    Button buttonSignIn = new Button("Sign In");

    GridPane signIn = new GridPane();



    public SignInScene(Stage stage) {
        this.stage = stage;
        initControls();
        initActions();
    }

    public void initControls() {
        signIn.add(head, 0,0,2,1);
        signIn.add(email, 0, 1);
        signIn.add(textFieldForEmail, 1, 1);
        signIn.add(password, 0, 2);
        signIn.add(textFieldForPassword, 1, 2);
        signIn.add(buttonSignIn, 1, 3);

        signIn.setAlignment(Pos.CENTER);
        signIn.setHgap(20);
        signIn.setVgap(20);
    }


    private boolean checkCredentials(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            Connection connection = DB.dbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public void initActions() {
        buttonSignIn.setOnAction(e -> {
            String emailInput = textFieldForEmail.getText();
            String passwordInput = textFieldForPassword.getText();

            if (checkCredentials(emailInput, passwordInput)) {
                stage.setScene(new MenuScene(stage).getScene());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("الباسورد او الايميل غلط يصاحبي");
                alert.showAndWait();
            }
        });
    }

    public Scene getScene() {
        Scene signInScene = new Scene(signIn, 400 ,500);
        signInScene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
        return signInScene;
    }

}
