package ru.pogorelov.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.pogorelov.Main;
import ru.pogorelov.connection.Database_connection;
import ru.pogorelov.model.Create_Zakaz_structure;

import java.net.URL;
import java.util.ResourceBundle;

public class Role_1_Manager_controller implements Initializable {

    public static ObservableList<Create_Zakaz_structure> list;

    private static Create_Zakaz_structure selectedZakaz;

    public static Create_Zakaz_structure getSelectedZakaz(){
        return selectedZakaz;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main.getPrimaryStage().hide();
        CreatePanel.setVisible(false);
        WelcomePane.setVisible(false);
        changePanel.setVisible(true);
        combobox.setItems(FXCollections.observableArrayList("Телефон", "Планшет", "Ноутбук"));
        combobox2.setItems(FXCollections.observableArrayList("в ремонте", "на согласовании", "к выдаче"));
        combobox3.setItems(FXCollections.observableArrayList("Алексей", "Юрий"));

        list = Database_connection.getZakazesFromBD();
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

    @FXML
    AnchorPane CreatePanel;

    @FXML
    Pane WelcomePane;

    @FXML
    AnchorPane changePanel;

    //важные поля для отправки в бд
    @FXML
    ComboBox combobox;

    @FXML
    TextField markaTF;

    @FXML
    TextField modelTF;

    @FXML
    TextField fioTF;

    @FXML
    TextField telTF;

    @FXML
    CheckBox checkbox;

    @FXML
    TextField addressTF;

    @FXML
    ComboBox combobox2;

    @FXML
    TextArea neispravTF;

    @FXML
    ComboBox combobox3;

    @FXML
    TextArea kommentTF;

    //поля таблицы
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
    private void createBtnAction(ActionEvent event){
        WelcomePane.setVisible(false);
        CreatePanel.setVisible(true);
        changePanel.setVisible(false);


    }


    @FXML
    private void changeBtnAction(ActionEvent event){

        WelcomePane.setVisible(false);
        CreatePanel.setVisible(false);
        changePanel.setVisible(true);

        list = Database_connection.getZakazesFromBD();
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





    @FXML
    private void getWork(ActionEvent event){
        WelcomePane.setVisible(false);
        CreatePanel.setVisible(true);
        changePanel.setVisible(false);
    }

    @FXML
    private void exit(ActionEvent action){
        Platform.exit();
    }

    @FXML
    private void sendToBD(ActionEvent event){
        Create_Zakaz_structure zakaz = new Create_Zakaz_structure(0,combobox.getValue().toString(), markaTF.getText(), modelTF.getText(), fioTF.getText(), telTF.getText(), checkbox.isSelected(), addressTF.getText(), combobox2.getValue().toString(), neispravTF.getText(), combobox3.getValue().toString(), kommentTF.getText());
        Database_connection.sendZakaz(zakaz);
        clearForm();
    }

    public void clearForm(){
        markaTF.clear();
        modelTF.clear();
        fioTF.clear();
        telTF.clear();
        addressTF.clear();
        neispravTF.clear();
        kommentTF.clear();
    }

    private static Stage stage;

    private void setStage(Stage stage) {
        Role_1_Manager_controller.stage = stage;
    }

    static public Stage getStage() {
        return Role_1_Manager_controller.stage;
    }

    @FXML
    public void changeBTN(ActionEvent event) throws Exception{

        selectedZakaz = (Create_Zakaz_structure)table.getSelectionModel().getSelectedItem();


        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/changer.fxml")));
        setStage(new Stage());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
