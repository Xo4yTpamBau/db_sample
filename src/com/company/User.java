package com.company;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private Address address;

    public User(String name, String login, String password, Address address) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public User(int id, String name, String login, String password, Address address) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                '}';
    }
}
