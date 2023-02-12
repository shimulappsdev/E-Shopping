package com.example.eshopping.Model;

public class User {

    private String email,password,uid,name,phone,profile;

    public User (){

    }

    public User(String email, String password, String uid, String name, String phone, String profile) {
        this.email = email;
        this.password = password;
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
