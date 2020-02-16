package ru.pogorelov.model;

public class Change_structure {
    private int id;
    private String tel;
    private String address;
    private String status;
    private String neispravnost;
    private String ispolnitel;
    private String komment;

    public Change_structure(int id, String tel, String address, String status, String neispravnost, String ispolnitel, String komment) {
        this.id = id;
        this.tel = tel;
        this.address = address;
        this.status = status;
        this.neispravnost = neispravnost;
        this.ispolnitel = ispolnitel;
        this.komment = komment;
    }

    public int getId() {
        return id;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public String getNeispravnost() {
        return neispravnost;
    }

    public String getIspolnitel() {
        return ispolnitel;
    }

    public String getKomment() {
        return komment;
    }
}
