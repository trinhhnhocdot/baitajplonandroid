package com.example.trinhhnph20554_asm.Model;

public class Job {
    private  int id;
    private  String name;
    private  String content;
    private  String startday;
    private  String endday;
    private  int id_status;
    private String name_status;

    public Job(int id, String name, String content, String startday, String endday, int id_status, String name_status) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.startday = startday;
        this.endday = endday;
        this.id_status = id_status;
        this.name_status = name_status;
    }



    public Job() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartday() {
        return startday;
    }

    public void setStartday(String startday) {
        this.startday = startday;
    }

    public String getEndday() {
        return endday;
    }

    public void setEndday(String endday) {
        this.endday = endday;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getName_status() {
        return name_status;
    }

    public void setName_status(String name_status) {
        this.name_status = name_status;
    }
}
