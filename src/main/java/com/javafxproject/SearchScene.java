package com.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchScene {
    Stage stage;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet res=null;
    TableView table=new TableView<>();
    ObservableList<Ticket> data;

    Label search =new Label("Search your Booking");
    Label details=new Label("Enter your details");
    Label name=new Label("Name:");
    Label email =new Label("Email:");
    Label ticketid=new Label("Ticket ID:");
    TextField tname=new TextField();
    TextField temail=new TextField();
    TextField tticketid=new TextField();
    Button bsearch=new Button("Search");
    Alert a1=new Alert(Alert.AlertType.ERROR,"Please enter at least on value");
    Button back=new Button("Back");
    GridPane root=new GridPane();
    VBox v1=new VBox(root);

    VBox v2=new VBox(table);
    FlowPane f=new FlowPane(v1,v2);


    public SearchScene(Stage stage) {
        this.stage = stage;
        initControls();
        initActions();
    }

    public void show() throws SQLException {
        data= FXCollections.observableArrayList();
        conn=DB.dbConnection();
        pst=conn.prepareStatement("select * from TICKETS");
        res=pst.executeQuery();
        while(res.next()){
            data.add(new Ticket(
                    res.getString("NAME"),
                    res.getString("NATIONALITY"),
                    res.getString("EMAIL"),
                    res.getString("DESTINATION"),
                    res.getString("TARVELDATE"),
                    res.getInt("PASSPORTID"),
                    res.getInt("id")
            ));
        }
        table.setItems(data);
        pst.close();
        conn.close();

    }

    public void initControls() {
        root.add(search,0,0,3,1);
        root.add(details,0,1);
        root.add(name,0,2);
        root.add(email,0,3);
        root.add(ticketid,0,4);
        root.add(tname,1,2);
        root.add(temail,1,3);
        root.add(tticketid,1,4);
        root.add(bsearch,1,5);
        root.add(back,0,5);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20));


        table.setPlaceholder(new Label("No rows to display"));
        TableColumn<Ticket,String> c1=new TableColumn<>("Name");
        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Ticket,String> c2=new TableColumn<>("Nationality");
        c2.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        TableColumn<Ticket,String> c3=new TableColumn<>("Email");
        c3.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Ticket,String> c4=new TableColumn<>("Travel distination");
        c4.setCellValueFactory(new PropertyValueFactory<>("traveldist"));
        TableColumn<Ticket,String> c5=new TableColumn<>("Travel Date");
        c5.setCellValueFactory(new PropertyValueFactory<>("traveldate"));
        TableColumn<Ticket,Integer> c6=new TableColumn<>("Passport Number");
        c6.setCellValueFactory(new PropertyValueFactory<>("passportnumber"));
        TableColumn<Ticket,Integer> c7=new TableColumn<>("Ticket ID");
        c7.setCellValueFactory(new PropertyValueFactory<>("ticketid"));
        table.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7);
        c1.setPrefWidth(150);
        c2.setPrefWidth(150);
        c3.setPrefWidth(150);
        c4.setPrefWidth(150);
        c5.setPrefWidth(150);
        c6.setPrefWidth(150);
        c7.setPrefWidth(150);


        f.setAlignment(Pos.CENTER);
        f.setVgap(10);
        f.setHgap(10);
        f.setPadding(new Insets(10));
        try {
        show();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    public void initActions() {
        bsearch.setOnAction((ActionEvent event) -> {

            try  {
                Connection conn = DB.dbConnection();

                String sql= "";
                PreparedStatement pst = null;

                if (tname.getText().isEmpty() && temail.getText().isEmpty() && tticketid.getText().isEmpty()) {
                    a1.show();
                }

                if (!tname.getText().isEmpty() ) {
                    sql = "SELECT * FROM TICKETS WHERE NAME = ?";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, tname.getText());
                } else if (!temail.getText().isEmpty() ) {
                    sql = "SELECT * FROM TICKETS WHERE EMAIL= ?";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, temail.getText());
                }else if (!tticketid.getText().isEmpty() ){
                    sql = "SELECT * FROM TICKETS WHERE id= ?";
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(tticketid.getText()));
                }



                ResultSet res = pst.executeQuery();

                data = FXCollections.observableArrayList();

                while (res.next()) {
                    data.add(new Ticket(
                            res.getString("NAME"),
                            res.getString("NATIONALITY"),
                            res.getString("EMAIL"),
                            res.getString("DESTINATION"),
                            res.getString("TARVELDATE"),
                            res.getInt("PASSPORTID"),
                            res.getInt("id")
                    ));
                }

                table.setItems(data);

                if (data.isEmpty()) {
                    System.out.println("No results found");
                }

            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
       back.setOnAction((ActionEvent event)->{
           stage.setScene(new MenuScene(stage).getScene());
        });
    }
    public Scene getScene() {
        Scene searchScene = new Scene(f);
        searchScene.getStylesheets().add(App.class.getResource("main.css").toExternalForm());
        stage.setMaximized(true);
        return searchScene;
    }
}