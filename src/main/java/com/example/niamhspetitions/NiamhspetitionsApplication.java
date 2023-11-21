package com.example.niamhspetitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

	public static void main(String[] args) {
		SpringApplication.run(NiamhspetitionsApplication.class, args);
	}

}
