package ru.pogorelov.model;

public class Create_Zakaz_structure {

    private int id;
    private String type;
    private String marka;
    private String model;
    private String fio;
    private String tel;
    private boolean viezd;
    private String address;
    private String status;
    private String neispravnost;
    private String ispolnitel;
    private String kommentarii;

    public Create_Zakaz_structure(int id, String type, String marka, String model, String fio, String tel, boolean viezd, String address, String status, String neispravnost, String ispolnitel, String kommentarii){
        this.id = id;
        this.type = type;
        this.marka = marka;
        this.model = model;
        this.fio = fio;
        this.tel = tel;
        this.viezd = viezd;
        this.address = address;
        this.status = status;
        this.neispravnost = neispravnost;
        this.ispolnitel = ispolnitel;
        this.kommentarii = kommentarii;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public String getFio() {
        return fio;
    }

    public String getTel() {
        return tel;
    }

    public boolean isViezd() {
        return viezd;
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

    public String getKommentarii() {
        return kommentarii;
    }
}
