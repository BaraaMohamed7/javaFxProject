package com.javafxproject;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SignUpScene {
    Stage stage;
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet res = null;

    Label welcomeLabel;
    Label signUpLabel;
    Label nameLabel;
    Label emailLabel;
    Label passwordLabel;

    TextField nameTextField;
    TextField emailTextField;
    PasswordField passwordTextField;

    Button registerButton;
    Label haveAccountLabel;
    Button loginButton;

    GridPane signUpGrid;



    public SignUpScene(Stage stage) {
        this.stage = stage;
        initControls();
        initActions();
    }

    public void initControls() {
        welcomeLabel = new Label("Welcome to Airway");
        signUpLabel= new Label("Sign Up");
        nameLabel  = new Label("Name :");
        emailLabel = new Label("Email :");
        passwordLabel = new Label("Password :");
        nameTextField = new TextField();
        nameTextField.setPromptText("Enter Name");
        emailTextField = new TextField();
        passwordTextField = new PasswordField();
        registerButton = new Button("Register");
        registerButton.getStyleClass().add("registerButton");
        haveAccountLabel = new Label("Have an Account ?");
        loginButton = new Button("Login");

        signUpGrid = new GridPane();
        signUpGrid.add(welcomeLabel, 0, 0 ,2 ,1);
        signUpGrid.add(signUpLabel, 0, 1 ,2 ,1);
        signUpGrid.add(nameLabel, 0, 2 ,2 ,1);
        signUpGrid.add(nameTextField, 0, 3 ,2 ,1);
        signUpGrid.add(emailLabel, 0, 4 ,2 ,1);
        signUpGrid.add(emailTextField, 0, 5 ,2 ,1);
        signUpGrid.add(passwordLabel, 0, 6 ,2 ,1);
        signUpGrid.add(passwordTextField, 0, 7 ,2 ,1);
        signUpGrid.add(registerButton, 0, 8 ,2 ,1);
        signUpGrid.add(haveAccountLabel, 0, 9);
        signUpGrid.add(loginButton, 1, 9);

        signUpGrid.setAlignment(Pos.CENTER);
        signUpGrid.setHgap(10);
        signUpGrid.setVgap(10);

    }

    public void initActions() {
        registerButton.setOnAction(e -> {
            conn = DB.dbConnection();
            String name = nameTextField.getText();
            String email = emailTextField.getText();
            String password = passwordTextField.getText();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Alert notFilledAlert = new Alert(Alert.AlertType.ERROR);
                notFilledAlert.setTitle("Error");
                notFilledAlert.setContentText("You must fill all fields");
                notFilledAlert.showAndWait();
            }   else {

                String sql = "SELECT * FROM users WHERE email = ?";
                try {
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, email);
                    res = statement.executeQuery();
                    if (res.next()) {
                        Alert accountFoundedAlert = new Alert(Alert.AlertType.ERROR);
                        accountFoundedAlert.setTitle("Error");
                        accountFoundedAlert.setContentText("Email already exists");
                        accountFoundedAlert.showAndWait();
                    } else {
                        String newSql = "INSERT INTO users (name, email, password) VALUES (?,?,?)";
                        statement = conn.prepareStatement(newSql);
                        statement.setString(1, name);
                        statement.setString(2, email);
                        statement.setString(3, password);

                        if (statement.executeUpdate() == 1) {
                            Alert userAddedAlert = new Alert(Alert.AlertType.INFORMATION);
                            userAddedAlert.setTitle("Done");
                            userAddedAlert.setContentText("User was added successfully");
                            userAddedAlert.showAndWait();
                            stage.setScene(new MenuScene(stage).getScene());
                        }
                    }


                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        });
        loginButton.setOnAction(e->{
            stage.setScene(new SignInScene(stage).getScene());
        });
    }

    public Scene getScene() {
        return new Scene(signUpGrid, 400,500);
    }
}
