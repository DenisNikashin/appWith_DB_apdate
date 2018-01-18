package com.homework.userAccount;

public class User {
    private String name;
    private String email;
    private String password;
    private String country;
    private int id;

    public User(String name, String email, String country, String password){
        this.name = name;
        this.country = country;
        this.email=email;
        this.password = password;
    }
    public User(String name, String email, String country, int id){
        this.name = name;
        this.country = country;
        this.email=email;
        this.id = id;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString(){
        return "Name=" + this.name +
                ", Email=" + this.email +
                ", Country=" + this.country;
    }
}
