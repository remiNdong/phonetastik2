package fr.phonetastik.reparation.ui;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.phonetastik.marque.entity.Marque;
import fr.phonetastik.marque.service.MarqueService;
import fr.phonetastik.marque.service.NomdefichierimageService;
import fr.phonetastik.modeletelephone.entity.ModeletelephoneDTO;
import fr.phonetastik.modeletelephone.service.ModeletelephoneService;
import fr.phonetastik.reparation.entity.ReparationCreationDTO;
import fr.phonetastik.reparation.entity.ReparationDTO;
import fr.phonetastik.reparation.service.ReparationService;

@Controller
@RequestMapping("/administration/reparation")
public class ReparationController {
	
	private static final String REPARATION_CREATE_FORM = "administration/reparation/creationReparation";
	private static final String REPARATION_LISTE_MARQUE = "administration/reparation/listeMarques";
	private static final String REPARATION_MODELE_LIST = "administration/reparation/listModeles";
	private static final String REPARATION_POUR_UN_MODELE_LIST="administration/reparation/listPourUnModele";
	private static final String REPARATION_LISTE_MARQUE_MODIFICATION = "administration/reparation/listeMarquesModification";
	private static final String REPARATION_MODELE_LIST_MODIFICATION = "administration/reparation/listModelesModification";
	private static final String REPARATION_UPDATE_FORM = "administration/reparation/modificationReparation";
	
	@Autowired
	ReparationService reparationService;
	
	@Autowired
	NomdefichierimageService nomdefichierimageService;
	

	@Autowired
	ModeletelephoneService modeletelephoneService;
	
	@Autowired
	MarqueService marqueService;
	
	
	
	@GetMapping("/creation")
	public String createReparation(Model model) {
		

		model.addAttribute("marques", marqueService.lister());
		return REPARATION_LISTE_MARQUE;
	}
	
	@GetMapping("/list")
	public String viewAllModeles(Model model) {
		
		List<Marque>marques= marqueService.lister();
		model.addAttribute("marques",marques);
		
		return REPARATION_LISTE_MARQUE_MODIFICATION;
	}
	
	
	@GetMapping("/list/{id}")
	public String viewModeles(Model model,@PathVariable long id) {
		
		Marque marque=marqueService.findMarqueById(id);
		List<ModeletelephoneDTO>modeles= modeletelephoneService.findModeletelephoneByMarque(marque);
		model.addAttribute("modeles",modeles);
		model.addAttribute("nomMarque",marque.getNom());
		
		return REPARATION_MODELE_LIST;
	}
	
	@GetMapping("/list/modification/{id}")
	public String viewModelesModification(Model model,@PathVariable long id) {
		
		Marque marque=marqueService.findMarqueById(id);
		List<ModeletelephoneDTO>modeles= modeletelephoneService.findModeletelephoneByMarque(marque);
		model.addAttribute("modeles",modeles);
		model.addAttribute("nomMarque",marque.getNom());
		
		return REPARATION_MODELE_LIST_MODIFICATION;
	}
	
	
	@GetMapping("/list/modele/{id}")
	public String viewModelesReparations(Model model,@PathVariable long id) {
		
		Optional<ModeletelephoneDTO> modeletelephone=modeletelephoneService.findModeletelephoneById(id);
		List<ReparationDTO> reparations = reparationService.findReparationByModele(modeletelephone.get());
		model.addAttribute("modele",modeletelephone.get());
		
		model.addAttribute("reparations",reparations);
		
		return REPARATION_POUR_UN_MODELE_LIST;
	}
	
	@GetMapping("/creation/{id}")
	public String createReparation(Model model,@PathVariable long id) {

		Optional<ModeletelephoneDTO> modeleDTO=modeletelephoneService.findModeletelephoneById(id);
		
		model.addAttribute("modele",modeleDTO.get());
		ReparationCreationDTO reparationCreationDTO=new ReparationCreationDTO();
		reparationCreationDTO.setIdModele(id);
		reparationCreationDTO.setNomModele(modeleDTO.get().getReference());
		model.addAttribute("reparationCreationDTO", reparationCreationDTO);
		return REPARATION_CREATE_FORM;
		

	}
	
	@GetMapping("/modification/{id}")
	public String updateReparation(Model model,@PathVariable long id) {

		
		Optional<ReparationDTO> reparationDTO=reparationService.findReparationById(id);
		model.addAttribute("reparation", reparationDTO.get());
		return REPARATION_UPDATE_FORM;
		
	}
	
	@PostMapping("/modification/{id}")
	public String updateModele(@Valid @ModelAttribute("reparation") ReparationDTO reparationDTO,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, @RequestParam String reparationVisible ) {

		try {

			if (bindingResult.hasErrors())
				return REPARATION_UPDATE_FORM;
			
			reparationDTO.setVisibleValue(reparationVisible);
			reparationService.updateReparation(reparationDTO);
			return "redirect:/administration/reparation/list/modele/"+reparationDTO.getIdModele();

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}
	
	
	
	@PostMapping("/creation")
	public String createNewModele(@Valid @ModelAttribute("reparationCreationDTO") ReparationCreationDTO reparationCreationDTO,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		try {

			if (bindingResult.hasErrors())
				return "redirect:/administration/reparation/creation/"+reparationCreationDTO.getIdModele();

			if (!(reparationService.findModeleByModeleAndNom(reparationCreationDTO.getIdModele(), reparationCreationDTO.getNom()).isEmpty())) {
				Optional<ModeletelephoneDTO> modeleDTO=modeletelephoneService.findModeletelephoneById(reparationCreationDTO.getIdModele());
				model.addAttribute("modele",modeleDTO.get());
				model.addAttribute("erreurCreationReparation",
						"Il ne peut y avoir qu'une reparation de  nom " + reparationCreationDTO.getNom()+ " pour le modele "+reparationCreationDTO.getNomModele());
				return REPARATION_CREATE_FORM;
			}
			
			
			
			
			reparationService.enregistrer(reparationCreationDTO);
			
			
			return "redirect:/administration/reparation/list/modele/"+reparationCreationDTO.getIdModele();

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}
	
	@GetMapping("/suppression/{id}")
	public String deleteReparation(Model model,@PathVariable long id) {
		
		Optional<ReparationDTO> reparationDTO=reparationService.findReparationById(id);
		
		if(reparationDTO.isPresent())
			reparationService.deleteReparation(reparationDTO.get());
		
		return "redirect:/administration/reparation/list/modele/"+reparationDTO.get().getIdModele();
	
	}


}
