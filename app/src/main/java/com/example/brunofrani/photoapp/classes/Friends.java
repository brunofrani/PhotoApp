package com.example.brunofrani.photoapp.classes;

/**
 * Created by Bruno Frani on 28/08/2017.
 */

public class Friends {

    String username;
    String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Friends(String username, String email) {

        this.username = username;
        this.email = email;
    }
}
