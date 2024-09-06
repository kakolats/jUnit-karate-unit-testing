package me.essejacques.demotestdeploy.entity;


public class User {
    private Long id;
    private String name;
    private String adresse;
    private String password;

    public User(Long id, String name, String adresse, String password) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}