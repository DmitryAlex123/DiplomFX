package ru.pogorelov.model;

public class Update_Master_structure {
    private int id;
    private String status;
    private String neispravnost;
    private String komment;

    public Update_Master_structure(int id, String status, String neispravnost, String komment){
        this.id = id;
        this.status = status;
        this.neispravnost = neispravnost;
        this.komment = komment;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getNeispravnost() {
        return neispravnost;
    }

    public String getKomment() {
        return komment;
    }
}
