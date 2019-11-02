package ru.javalab.socketsapp.entities;

public class User {
    private long id;
    private String login;
    private String password;

    public User(long id, String password, String login){
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
