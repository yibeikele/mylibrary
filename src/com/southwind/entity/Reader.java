package com.southwind.entity;

public class Reader {
    private int id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String cardId;
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", cardId='" + cardId + '\'' +
                ", gender=" + gender +
                '}';
    }

    public Reader() {

    }

    public Reader(String username, String password, String name, String tel, String cardId, String gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.cardId = cardId;
        this.gender = gender;
    }

    public Reader(int id, String username, String password, String name, String tel, String cardId, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.cardId = cardId;
        this.gender = gender;
    }
}
