package ru.pogorelov.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.pogorelov.Main;
import ru.pogorelov.connection.Database_connection;
import ru.pogorelov.model.Login_structure;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login_controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private TextField loginTF;
    @FXML
    private TextField passTF;
    @FXML
    private Label messageLabel;


    @FXML
    private void login(ActionEvent action){


        Login_structure loginData = new Login_structure(loginTF.getText(), passTF.getText());

        try {
            int unic_id = Database_connection.login(loginData);

            if (unic_id != 0){
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("Вход успешен. Ваша роль: " + unic_id );



                try{
                    openFXML(unic_id);
                }catch (Exception ex){
                    System.out.println("Форма не хочет открываться: " + ex);
                }

            }else{

                messageLabel.setText("Логин или пароль не верны");
            }

        }catch (Exception ex){
            messageLabel.setText("Подключение не успешно");
        }

    }

    @FXML
    private void exit(ActionEvent action){
        Platform.exit();
    }

    private static Stage stage;

    private void setStage(Stage stage) {
        Login_controller.stage = stage;
    }

    static public Stage getStage() {
        return Login_controller.stage;
    }

    //*******
    private static Stage connectionChangeStage;

    private void setCStage(Stage stage) {
        Login_controller.connectionChangeStage = stage;
    }

    static public Stage getCStage() {
        return Login_controller.connectionChangeStage;
    }

    ///******
    @FXML
    private void openingConnectionChanger() throws Exception{
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Connection data.fxml")));
        setCStage(new Stage());
        connectionChangeStage.initModality(Modality.APPLICATION_MODAL);
        connectionChangeStage.setScene(scene);
        connectionChangeStage.initStyle(StageStyle.UNDECORATED);
        connectionChangeStage.show();
    }

    //жалкие попытки универсалить код
    protected void openFXML(int unic_id) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/" + unic_id + ".fxml")));
        setStage(new Stage());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

}
