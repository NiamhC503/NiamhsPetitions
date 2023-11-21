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

    //method to retrieve 1 petition and its signatures by title
    public static Petition getPetitionByTitle(String title) {
        Petition petition = petitionMap.get(title);

        if (petition != null && petition.getSignatures() == null) {
            petition.setSignatures(new ArrayList<>());
        }

        return petition;
    }


}
