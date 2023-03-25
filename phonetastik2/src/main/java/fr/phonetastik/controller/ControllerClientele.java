package fr.phonetastik.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.phonetastik.marque.entity.Marque;
import fr.phonetastik.marque.service.MarqueService;
import fr.phonetastik.modeletelephone.entity.ModeletelephoneDTO;
import fr.phonetastik.modeletelephone.service.ModeletelephoneService;
import fr.phonetastik.reparation.entity.ReparationDTO;
import fr.phonetastik.reparation.service.ReparationService;

@Controller
@RequestMapping("/clientele")
public class ControllerClientele {
	
	private static final String MODELE_LIST = "clientele/listModeles";
	private static final String MODELE_VIEW = "clientele/modeleView";
	private static final String REPARATION_POUR_UN_MODELE_LIST="clientele/listPourUnModele";
	
	@Autowired
	MarqueService marqueService;
	
	
	
	
	
	
	@Autowired
	ReparationService reparationService;

	@Autowired
	ModeletelephoneService modeletelephoneService;
	
	
	@GetMapping(value = "/listeMarques")
	public String viewTemplateListeMarques(Model model) {

		// model.addAttribute("map", storageService.getMap());

		try {

			model.addAttribute("listeMarques", marqueService.listerMarquesVisible());
			return "listeMarques";

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}
	
	
	
	@GetMapping("/listeMarques/list/{id}")
	public String viewModeles(Model model,@PathVariable long id) {
		
		Marque marque=marqueService.findMarqueById(id);
		List<ModeletelephoneDTO>modeles= modeletelephoneService.findModeletelephoneVisibleByMarque(marque);
		model.addAttribute("modeles",modeles);
		model.addAttribute("nomMarque",marque.getNom());
		
		return MODELE_LIST;
	}
	
	
	
	@GetMapping("/modele/{id}")
	public String viewModeleModification(Model model,@PathVariable long id) {
		
		Optional<ModeletelephoneDTO> modeleDTO=modeletelephoneService.findModeletelephoneById(id);
		
		if(modeleDTO.isPresent())
		model.addAttribute("modele",modeleDTO.get());
		
		return MODELE_VIEW;
	
	}
	
	@GetMapping("/list/modele/{id}")
	public String viewModelesReparations(Model model,@PathVariable long id) {
		
		Optional<ModeletelephoneDTO> modele=modeletelephoneService.findModeletelephoneById(id);
		List<ReparationDTO> reparations = reparationService.findReparationVisibleByModele(modele.get());
		model.addAttribute("modele",modele.get());
		
		model.addAttribute("reparations",reparations);
		
		return REPARATION_POUR_UN_MODELE_LIST;
	}
	

}
