package com.example.niamhspetitions;

import java.util.ArrayList;
import java.util.List;

public class Petition {
    private String title;
    private String description;

    private List<Signature> signatures = new ArrayList<>();


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

    public List<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<Signature> signatures) {
        this.signatures = signatures;
    }

    //method to add new signature to signatures List
    public void addSignature(Signature signature) {
        signatures.add(signature);
    }

}
