package ru.pogorelov.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.pogorelov.connection.Database_connection;
import ru.pogorelov.model.Change_structure;
import ru.pogorelov.model.Create_Zakaz_structure;

import java.net.URL;
import java.util.ResourceBundle;

public class Change_Window_controller implements Initializable {

    private int id;

    //Поля для изменения
    @FXML
    TextField telephoneTF;

    @FXML
    TextField addressTF;

    @FXML
    ComboBox statusCB;

    @FXML
    TextArea neispravnostTF;

    @FXML
    ComboBox  ispolnCB;

    @FXML
    TextArea kommentTF;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            Login_controller.getStage().setOpacity(0.5);


            Create_Zakaz_structure selectedTableItem = Role_1_Manager_controller.getSelectedZakaz();
            id = selectedTableItem.getId();
            telephoneTF.setText(selectedTableItem.getTel());
            addressTF.setText(selectedTableItem.getAddress());

            statusCB.setItems(FXCollections.observableArrayList("в ремонте", "на согласовании", "к выдаче"));
            statusCB.getSelectionModel().select(selectedTableItem.getStatus());
            neispravnostTF.setText(selectedTableItem.getNeispravnost());

            ispolnCB.setItems(FXCollections.observableArrayList("Алексей", "Юрий"));
            ispolnCB.getSelectionModel().select(selectedTableItem.getIspolnitel());
            kommentTF.setText(selectedTableItem.getKommentarii());

    }




    @FXML
    public void change(ActionEvent event){
        Login_controller.getStage().setOpacity(1);

        //собрать данные с этой формы в структуру change_structure и сделать апдейт метод в скюл классе (11.02.2020)
        Change_structure updateData = new Change_structure(id, telephoneTF.getText(), addressTF.getText(), statusCB.getValue().toString(), neispravnostTF.getText(), ispolnCB.getValue().toString(), kommentTF.getText());

        Database_connection.update(updateData);
        //как обновить таблицу отсюда (вызвать метод кнопки из Role_1_Manager_controller)



        Role_1_Manager_controller.getStage().close();

    }

    @FXML
    public void exit(ActionEvent event){
        Role_1_Manager_controller.getStage().close();
        Login_controller.getStage().setOpacity(1);
    }
}
