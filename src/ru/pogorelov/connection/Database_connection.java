package ru.pogorelov.connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.pogorelov.model.Change_structure;
import ru.pogorelov.model.Create_Zakaz_structure;
import ru.pogorelov.model.Login_structure;
import ru.pogorelov.model.Update_Master_structure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database_connection {
    private static String url = "jdbc:mysql://localhost/diplomfx?serverTimezone=Europe/Moscow&useSSL=false";
    private static String username = "root";
    private static String db_password = "qwerty123";
    private static int role;

    public static int login(Login_structure data) throws Exception {
        //по нажатию кнопки "Вход" логин и пароль попадают сюда
        String login = data.getLogin();
        String password = data.getPassword();
        System.out.println("Логин: " + login + " Пароль: " + password);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                Connection connection = DriverManager.getConnection(url, username, db_password);
                System.out.println("Вошли в базу");
                PreparedStatement ps = connection.prepareStatement("SELECT unic_id FROM login_data WHERE login = ? AND password = ?");
                ps.setString(1, login);
                ps.setString(2, password);
                System.out.println("Послан запрос");
                ResultSet response = ps.executeQuery();
                System.out.println("Принят ответ");
                if (response.next()) {
                    role = response.getInt(1);
                }
            } catch (Exception ex) {
                System.out.println("С запросом что то не то: " + ex);
            }
        } catch (Exception ex) {
            System.out.println("С драйвером что то не то: " + ex);
        }
        return role;

    }

    public static void sendZakaz(Create_Zakaz_structure zakaz) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                Connection connection = DriverManager.getConnection(url, username, db_password);
                PreparedStatement ps = connection.prepareStatement("INSERT INTO zakazy VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, zakaz.getId());
                ps.setString(2, zakaz.getType());
                ps.setString(3, zakaz.getMarka());
                ps.setString(4, zakaz.getModel());
                ps.setString(5, zakaz.getFio());
                ps.setString(6, zakaz.getTel());
                ps.setBoolean(7, zakaz.isViezd());
                ps.setString(8, zakaz.getAddress());
                ps.setString(9, zakaz.getStatus());
                ps.setString(10, zakaz.getNeispravnost());
                ps.setString(11, zakaz.getIspolnitel());
                ps.setString(12, zakaz.getKommentarii());
                ps.executeUpdate();
                System.out.println("Послан запрос");
            } catch (Exception ex) {
                System.out.println("С запросом что то не то: " + ex);
            }
        } catch (Exception ex) {
            System.out.println("С драйвером что то не то: " + ex);
        }
    }

    public static ObservableList<Create_Zakaz_structure> getZakazesFromBD() {

        ObservableList<Create_Zakaz_structure> list = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                Connection connection = DriverManager.getConnection(url, username, db_password);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM zakazy");

                while (result.next()) {
                    int id = Integer.parseInt(result.getString(1));
                    String type = result.getString(2);
                    String marka = result.getString(3);
                    String model = result.getString(4);
                    String fio = result.getString(5);
                    String tel = result.getString(6);
                    boolean viezd = result.getBoolean(7);
                    String address = result.getString(8);
                    String status = result.getString(9);
                    String neispravnost = result.getString(10);
                    String ispolnitel = result.getString(11);
                    String komment = result.getString(12);
                    Create_Zakaz_structure object = new Create_Zakaz_structure(id, type, marka, model, fio, tel, viezd, address, status, neispravnost, ispolnitel, komment);
                    list.add(object);
                }
                System.out.println("Таблица получена");
            } catch (Exception ex) {
                System.out.println("С запросом что то не то: " + ex);
            }
        } catch (Exception ex) {
            System.out.println("С драйвером что то не то: " + ex);
        }
        return list;

    }


    public static void update(Change_structure changeData) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                Connection connection = DriverManager.getConnection(url, username, db_password);
                PreparedStatement ps = connection.prepareStatement("UPDATE zakazy SET tel= ?, address = ?, status = ?, neispravnost = ?, ispolnitel = ?, kommentarii = ? WHERE id = ?");
                ps.setString(1, changeData.getTel());
                ps.setString(2, changeData.getAddress());
                ps.setString(3, changeData.getStatus());
                ps.setString(4, changeData.getNeispravnost());
                ps.setString(5, changeData.getIspolnitel());
                ps.setString(6, changeData.getKomment());
                ps.setInt(7, changeData.getId());
                ps.executeUpdate();
                System.out.println("Послан запрос");
            } catch (Exception ex) {
                System.out.println("С запросом что то не то: " + ex);
            }
        } catch (Exception ex) {
            System.out.println("С драйвером что то не то: " + ex);
        }
    }


    //выборка под мастера
    public static ObservableList<Create_Zakaz_structure> getMaster_1() {

        ObservableList<Create_Zakaz_structure> list = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                Connection connection = DriverManager.getConnection(url, username, db_password);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM zakazy WHERE ispolnitel IN ('Алексей')");

                while (result.next()) {
                    int id = Integer.parseInt(result.getString(1));
                    String type = result.getString(2);
                    String marka = result.getString(3);
                    String model = result.getString(4);
                    String fio = result.getString(5);
                    String tel = result.getString(6);
                    boolean viezd = result.getBoolean(7);
                    String address = result.getString(8);
                    String status = result.getString(9);
                    String neispravnost = result.getString(10);
                    String ispolnitel = result.getString(11);
                    String komment = result.getString(12);
                    Create_Zakaz_structure object = new Create_Zakaz_structure(id, type, marka, model, fio, tel, viezd, address, status, neispravnost, ispolnitel, komment);
                    list.add(object);
                }
                System.out.println("Таблица получена");
            } catch (Exception ex) {
                System.out.println("С запросом что то не то: " + ex);
            }
        } catch (Exception ex) {
            System.out.println("С драйвером что то не то: " + ex);
        }
        return list;

    }


    public static ObservableList<Create_Zakaz_structure> getMaster_2() {

        ObservableList<Create_Zakaz_structure> list = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                Connection connection = DriverManager.getConnection(url, username, db_password);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM zakazy WHERE ispolnitel IN ('Юрий')");

                while (result.next()) {
                    int id = Integer.parseInt(result.getString(1));
                    String type = result.getString(2);
                    String marka = result.getString(3);
                    String model = result.getString(4);
                    String fio = result.getString(5);
                    String tel = result.getString(6);
                    boolean viezd = result.getBoolean(7);
                    String address = result.getString(8);
                    String status = result.getString(9);
                    String neispravnost = result.getString(10);
                    String ispolnitel = result.getString(11);
                    String komment = result.getString(12);
                    Create_Zakaz_structure object = new Create_Zakaz_structure(id, type, marka, model, fio, tel, viezd, address, status, neispravnost, ispolnitel, komment);
                    list.add(object);
                }
                System.out.println("Таблица получена");
            } catch (Exception ex) {
                System.out.println("С запросом что то не то: " + ex);
            }
        } catch (Exception ex) {
            System.out.println("С драйвером что то не то: " + ex);
        }
        return list;

    }


    public static void updateData(Update_Master_structure changeData) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                Connection connection = DriverManager.getConnection(url, username, db_password);
                PreparedStatement ps = connection.prepareStatement("UPDATE zakazy SET status = ?, neispravnost = ?, kommentarii = ? WHERE id = ?");
                ps.setString(1, changeData.getStatus());
                ps.setString(2, changeData.getNeispravnost());
                ps.setString(3, changeData.getKomment());
                ps.setInt(4, changeData.getId());
                ps.executeUpdate();
                System.out.println("Послан запрос");
            } catch (Exception ex) {
                System.out.println("С запросом что то не то: " + ex);
            }
        } catch (Exception ex) {
            System.out.println("С драйвером что то не то: " + ex);
        }
    }


}

