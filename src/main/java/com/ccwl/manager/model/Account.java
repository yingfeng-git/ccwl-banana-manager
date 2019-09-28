package com.ccwl.manager.model;

public class Account {
    private int id;
    private String numb;
    private String name;
    private int age;
    private String password;
    private String permission;

    public Account() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumb() {
        return numb;
    }

    public void setNum(String numb) {
        this.numb = numb;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String toString() {
//        return "Account{id=" + this.id +
//                ", numb='" + this.numb + '\'' +
//                ", name='" + this.name + '\'' +
//                ", age='" + this.age + '\'' +
//                ", password='" + this.password + '\'' +
//                ", permission='" + this.permission + "'}";
        return "{\"id\": " + this.id + ", " +
                "\"numb\": \"" + this.numb + "\", " +
                "\"name\": \"" + this.name + "\", " +
                "\"age\": " + this.age + ", " +
                "\"password\": \"" + this.password + "\", " +
                "\"permission\": \"" + this.permission + "\"}";
    }
}

