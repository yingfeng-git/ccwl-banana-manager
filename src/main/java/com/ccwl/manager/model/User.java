package com.ccwl.manager.model;

/**
 * [{
 *   name: '学号 :',data: 'number'}, {
 *   name: '姓名 :',data: 'name'}, {
 *   name: '性别 :',data: 'sex'}, {
 *   name: '民族 :',data: 'nationality'}, {
 *   name: '籍贯 :',data: 'nativePlace'}, {
 *   name: '学院 :',data: 'college'}, {
 *   name: '专业 :',data: 'professional'}, {  // 老师没有此字段
 *   name: '班级 :',data: 'class'}, {         // 老师没有此字段
 *   name: '政治面貌 :',data: 'politicsStatus'}, {
 *   name: '联系电话 :',data: 'phoneNumber'}, {
 *   name: '紧急联系人 :',data: 'contactEmergency'},  {
 *   name: '联系人电话 :',data: 'contactPhoneNumber'}]
 */

public class User {
    private int id;
    private String number;
    private String name;
    private String sex;
    private int age;
    private String phoneNumber;
    private String permission;

    public User() { }

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
