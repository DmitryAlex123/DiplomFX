package ru.pogorelov.model;

public class Login_structure {
    //тут описывается модель данных, которая будет взаимодействовать с БД
    //вопрос зачем? - я не знаю
    private String login;
    private String password;

    public Login_structure(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
