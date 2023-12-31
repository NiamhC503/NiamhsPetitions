package com.example.niamhspetitions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
public class NiamhspetitionsApplication {

	//presents home page at root
	@GetMapping("/")
	public String home() {
		return "index";
	}

	//presents createpetition.html page at "/create" path
	@GetMapping("/create")
	public String petitionForm(Model model) {
		model.addAttribute("petition", new Petition());
		return "createpetition";
	}

	//handles createpetition.html form and adds user-made petition to petition repository
	@PostMapping("/create")
	public String createPetition(@ModelAttribute Petition petition, Model model) {
		// Add the new petition to the repository
		PetitionRepository.addPetition(petition);

		// Redirect to the viewallpetitions.html page
		return "redirect:/viewallpetitions";
	}

	//retrieves all stored petitions and presents them on "/viewallpetitions" page
	@GetMapping("/viewallpetitions")
	public String viewAllPetitions(Model model) {
		model.addAttribute("petitions", PetitionRepository.getAllPetitions());
		return "viewallpetitions";
	}

	@ModelAttribute("signature")
	public Signature signature() {

		return new Signature();
	}

	//retrieves 1 petition based on Petition Title and displays it on viewpetition.html page
	@GetMapping("/viewpetition/{title}")
	public String viewPetition(@PathVariable String title, Model model) {
		Petition petition = PetitionRepository.getPetitionByTitle(title);
		model.addAttribute("petition", petition);
		return "viewpetition";
	}

	//handles viewpetition.html form to add signature to that petition,
	// refreshes page to show newly added signature
	@PostMapping("/viewpetition/{title}")
	public String signPetition(@PathVariable String title, @ModelAttribute Signature signature, Model model) {
		Petition petition = PetitionRepository.getPetitionByTitle(title);
		petition.addSignature(signature);
		return "redirect:/viewpetition/{title}";
	}

	//method handling form from searchpetitions,
	//decides what to display based on if title exists in Petition Repository or not
	@GetMapping("/search")
	public String searchPetitions(@RequestParam(name = "searchTerm", required = false) String searchTerm, Model model) {
		if (searchTerm != null) {
			Petition foundPetition = searchPetitionByTitle(searchTerm);
			if (foundPetition != null) {
				// Redirect to result.html page with matching petition
				model.addAttribute("petition", foundPetition);
				//return "redirect:/result";
				return "result";
			} else {
				// Redirect to no-match.html page
				return "no-match";
			}
		}

		return "searchpetitions";
	}

	//method to check if search term is the title of existing petition in repository
	private Petition searchPetitionByTitle(String title) {
		return PetitionRepository.getPetitionByTitle(title);
	}

	public static void main(String[] args) {

		SpringApplication.run(NiamhspetitionsApplication.class, args);
	}


	//adding in random petition to be stored from start of application (not entered by user)
	@Component
	public class PetitionInitializer implements CommandLineRunner {
		@Override
		public void run(String... args) throws Exception {
			// Create a new petition during application start
			Petition petition1 = new Petition("Petition 1", "This is the description of the test petition.");

			//create a signature and add it to petition1
			Signature johnSignature = new Signature();
			johnSignature.setName("John");
			johnSignature.setEmail("john@gmail.com");
			petition1.addSignature(johnSignature);

			//add new petition to Repository
			PetitionRepository.addPetition(petition1);
		}
	}

}
