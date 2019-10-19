package com.ccwl.manager.model;

public class Account {
    private int id;
    private String number;
    private String name;
    private String sex;
    private int age;
    private String phoneNumber;
    private String permission;

    public Account() { }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getPhonenumber() { return phoneNumber; }

    public void setPhonenumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getAge() { return age; }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String toString() {
        return "{\"id\": " + this.id + ", " +
                "\"numb\": \"" + this.number + "\", " +
                "\"name\": \"" + this.name + "\", " +
                "\"sex\": \"" + this.sex + "\", " +
                "\"age\": " + this.age + ", " +
                "\"phoneNumber\": \"" + this.phoneNumber + "\", " +
                "\"permission\": \"" + this.permission + "\"}";
    }
}
