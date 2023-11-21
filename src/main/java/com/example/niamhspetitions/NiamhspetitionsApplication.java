package com.example.niamhspetitions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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


	//retrieves 1 petition based on Petition Title and displays it on viewpetition.html page
	@GetMapping("/viewpetition/{title}")
	public String viewPetition(@PathVariable String title, Model model) {
		Petition petition = PetitionRepository.getPetitionByTitle(title);
		model.addAttribute("petition", petition);
		return "viewpetition";
	}

	public static void main(String[] args) {

		SpringApplication.run(NiamhspetitionsApplication.class, args);
	}


	//adding in random petition to be stored from beginning (not entered by user)
	@Component
	public class PetitionInitializer implements CommandLineRunner {
		@Override
		public void run(String... args) throws Exception {
			// Create a new petition during application start
			Petition petition1 = new Petition("Petition 1", "This is the description of the test petition.");

			//create a signature and add it to petition1
			Signature johnSignature = new Signature();
			johnSignature.setName("John");
			johnSignature.setEmail("john@gemail.com");
			petition1.addSignature(johnSignature);

			//add new petition to Repository
			PetitionRepository.addPetition(petition1);
		}
	}

}
