package com.example.niamhspetitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
@Controller
public class NiamhspetitionsApplication {

	//presents home page at path root
	@GetMapping("/")
	public String home() {
		return "index";
	}

	//presents createpetition.html page ot "/create" path
	@GetMapping("/create")
	public String petitionForm(Model model) {
		return "createpetition";
	}

	//handles createpetition.html form and adds user-made petition to Repository
	@PostMapping("/create")
	public String createPetition(@ModelAttribute Petition petition, Model model) {
		// Add the new petition to the repository
		PetitionRepository.addPetition(petition);

		// Redirect to the viewallpetitions.html page
		return "redirect:/viewallpetitions";
	}

	//retrieves all stores petitions and presents them on "/viewallpetitions" page
	@GetMapping("/viewallpetitions")
	public String viewAllPetitions(Model model) {
		model.addAttribute("petitions", PetitionRepository.getAllPetitions());
		return "viewallpetitions";
	}

	public static void main(String[] args) {
		SpringApplication.run(NiamhspetitionsApplication.class, args);
	}

}
