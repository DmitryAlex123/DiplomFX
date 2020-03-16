package ru.pogorelov.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.pogorelov.connection.Database_connection;

import java.net.URL;
import java.util.ResourceBundle;

public class Connection_data_controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ta.setText("CREATE DATABASE [IF NOT EXIST] diplomfx; \n" +
                "USE diplomfx; \n" +
                "CREATE TABLE login_data(login VARCHAR(10) PRIMARY KEY, password VARCHAR(10), unic_id int(5); \n" +
                "INSERT INTO login_data(login, password, unic_id) VALUES(Manager, qwerty123, 1); \n" +
                "INSERT INTO login_data(login, password, unic_id) VALUES(Master_1, qwerty123, 2); \n" +
                "INSERT INTO login_data(login, password, unic_id) VALUES(Master_2, qwerty123, 3); \n" +
                "CREATE TABLE zakazy(id INT(200) AUTO INCREMENT, type VARCHAR(12), marka type VARCHAR(12), model type VARCHAR(12), fio type VARCHAR(60), tel type VARCHAR(12), viezd TINYINT(1), address VARCHAR(80), ststus VARCHAR(40), neispravnost VARCHAR(60), ispolnitel VARCHAR(60), kommentarii VARCHAR(70); \n"
                );
    }

    @FXML
    TextArea ta;
    @FXML
    TextField login;
    @FXML
    TextField password;
    @FXML
    TextField stroka;



    @FXML
    private void exit(ActionEvent action){
        Login_controller.getCStage().close();
    }

    @FXML
    private void save(ActionEvent action){

        Database_connection.setUrl(stroka.getText());
        Database_connection.setPassword(password.getText());
        Database_connection.setUsername(login.getText());
        Login_controller.getCStage().close();
    }


}
