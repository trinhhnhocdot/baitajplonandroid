package com.example.trinhhnph20554_asm.Model;

public class Status {
    private int id_status;
    private String status_name;

    public Status() {
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public Status(int id_status, String status_name) {
        this.id_status = id_status;
        this.status_name = status_name;
    }

    @Override
    public String toString() {
        return status_name ;
    }
}
