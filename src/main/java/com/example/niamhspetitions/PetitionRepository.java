package com.example.niamhspetitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetitionRepository {
    private static final Map<String, Petition> petitionMap = new HashMap<>();

    //method to add petition
    public static void addPetition(Petition petition) {

        petitionMap.put(petition.getTitle(), petition);
    }

    //method to retrieve all petitions
    public static List<Petition> getAllPetitions() {
        return petitionMap.values().stream().collect(Collectors.toList());
    }


}
