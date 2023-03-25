package fr.phonetastik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcceuilController {

	// mapping pour methode Get
	@GetMapping(value={"/home","/"})
	public String viewTemplate(Model model) {
		return "index";
	}
	



	// mapping pour methode Get
	@GetMapping("/acceuil")
	public String viewTemplateAcceuil(Model model) {
		return "acceuil";
	}
	

		
	
	

}
