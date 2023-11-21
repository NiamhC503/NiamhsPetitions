package com.example.niamhspetitions;

public class Petition {
    private String title;
    private String description;


    // Default Constructor
    public Petition() {
    }

    // Parameterized Constructor
    public Petition(String title, String description){

        this.title = title;
        this.description = description;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
