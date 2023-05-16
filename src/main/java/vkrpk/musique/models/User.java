package vkrpk.musique.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity @Table(name = "user")
public class User implements Serializable{

    @Column(name = "username", unique = true)
    @NotEmpty(message = "Le nom d'utilisateur ne peut pas être vide")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Le mot de passe ne peut pas être vide")
    private String password;

    @Column(name = "id")
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(){}

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){ return password; }

    public void setPassword(String password){
        this.password = password;
    }

    public Integer getId(){ return id; }
}
