package com.example.sqlitecrud;

public class StudentsModel {
    Integer Id;
    String name;
    String email;


    public StudentsModel(int id, String name, String email) {
        Id = id;
        this.name = name;
        this.email = email;
    }

    public StudentsModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
