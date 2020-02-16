package ru.pogorelov.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ru.pogorelov.Main;
import ru.pogorelov.connection.Database_connection;
import ru.pogorelov.model.Create_Zakaz_structure;
import ru.pogorelov.model.Update_Master_structure;

import java.net.URL;
import java.util.ResourceBundle;

public class Role_2_Master_controller implements Initializable {

    private static ObservableList<Create_Zakaz_structure> list;


    private static Create_Zakaz_structure selectedItem;
    private static int selectedId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.getPrimaryStage().hide();

        refrashTable();

        status_CB.setItems(FXCollections.observableArrayList("в ремонте", "на согласовании", "к выдаче"));


        table.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            selectedItem = (Create_Zakaz_structure)table.getSelectionModel().getSelectedItem();
            selectedId = selectedItem.getId();
            neispravnostTA.setText(selectedItem.getNeispravnost());
            kommentTA.setText(selectedItem.getKommentarii());
            status_CB.getSelectionModel().select(selectedItem.getStatus());
        });


    }

    @FXML
    TableView table;


    @FXML
    TableColumn id;

    @FXML
    TableColumn type;

    @FXML
    TableColumn marka;

    @FXML
    TableColumn model;

    @FXML
    TableColumn fio;

    @FXML
    TableColumn tel;

    @FXML
    TableColumn viezd;

    @FXML
    TableColumn address;

    @FXML
    TableColumn status;

    @FXML
    TableColumn neispravnost;

    @FXML
    TableColumn ispolnitel;

    @FXML
    TableColumn komment;


    @FXML
    TextArea neispravnostTA;

    @FXML
    ComboBox status_CB;

    @FXML
    TextArea kommentTA;

    @FXML
    public void updateBTN_Action(ActionEvent actionEvent){
        refrashTable();
    }


    @FXML
    public void changerBTN_Action(ActionEvent actionEvent){
        //13.02 сделать апдейт этих значений в скюл
        Update_Master_structure updateData = new Update_Master_structure(selectedId, status_CB.getValue().toString(), neispravnostTA.getText(), kommentTA.getText());
        Database_connection.updateData(updateData);
        neispravnostTA.clear();
        kommentTA.clear();
        refrashTable();
    }

    @FXML
    private void exit(ActionEvent action){
        Platform.exit();
    }


    private void refrashTable(){
        list = Database_connection.getMaster_1();
        id.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("id"));
        type.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("type"));
        marka.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("marka"));
        model.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("model"));
        fio.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("fio"));
        tel.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("tel"));
        viezd.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("viezd"));
        status.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("status"));
        neispravnost.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("neispravnost"));
        ispolnitel.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("ispolnitel"));
        komment.setCellValueFactory(new PropertyValueFactory<Create_Zakaz_structure, String>("komment"));
        table.setItems(list);
    }

}
