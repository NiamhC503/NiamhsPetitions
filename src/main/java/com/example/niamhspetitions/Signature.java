package com.example.niamhspetitions;

public class Signature {
    private String name;
    private String email;

    // Default Constructor
    public Signature() {
    }

    // Parameterized Constructor
    public Signature(String name, String email){

        this.name = name;
        this.email = email;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
