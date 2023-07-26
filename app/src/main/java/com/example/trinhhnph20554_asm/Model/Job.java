package com.example.trinhhnph20554_asm.Model;

public class Job {
    private  int id;
    private  String name;
    private  String content;
    private  int status;
    private  String startday;
    private  String endday;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Job(int id, String name, String content, int status, String startday, String endday) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.status = status;
        this.startday = startday;
        this.endday = endday;
    }

    public Job() {
    }
}
