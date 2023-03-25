package fr.phonetastik.modeletelephone.ui;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.phonetastik.marque.entity.Marque;
import fr.phonetastik.marque.service.MarqueService;
import fr.phonetastik.marque.service.NomdefichierimageService;
import fr.phonetastik.modeletelephone.entity.ModeleCreationDTO;
import fr.phonetastik.modeletelephone.entity.ModeletelephoneDTO;
import fr.phonetastik.modeletelephone.service.ModeletelephoneMapper;
import fr.phonetastik.modeletelephone.service.ModeletelephoneService;
import fr.phonetastik.utils.StorageException;
import fr.phonetastik.utils.StorageService;

@Controller
@RequestMapping("/administration/modele")
public class ModeleController {

	private static final String MODELE_CREATE_FORM = "administration/modele/creationModele";
	private static final String MODELE_LIST = "administration/modele/listModeles";
	private static final String MARQUE_LIST = "administration/modele/listMarques";
	private static final String MODELE_UPDATE_FORM = "administration/modele/modificationModele";

	@Autowired
	MarqueService marqueService;

	@Autowired
	ModeletelephoneService modeletelephoneService;
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	NomdefichierimageService nomdefichierimageService;
	
	@Autowired
	ModeletelephoneMapper modeletelephoneMapper;

	@GetMapping("/creation")
	public String createModele(Model model) {

		model.addAttribute("modeleCreationDTO", new ModeleCreationDTO());
		model.addAttribute("marques", marqueService.lister());
		return MODELE_CREATE_FORM;

	}

	@PostMapping("/creation")
	public String createNewModele(@RequestParam("imageModele") MultipartFile file,@Valid @ModelAttribute("modeleCreationDTO") ModeleCreationDTO modeleCreationDTO,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		try {

			if (bindingResult.hasErrors())
				return "redirect:/administration/modele/creation";

			if (!modeletelephoneService.findModeletelephoneByReference(modeleCreationDTO.getReference()).isEmpty()) {
				model.addAttribute("erreurCreationModele",
						"Il ne peut y avoir qu'un modele de  reference " + modeleCreationDTO.getReference());
				return MODELE_CREATE_FORM;
			}
			
			if (nomdefichierimageService.findNomDeFichierImageById(modeleCreationDTO.getReference().toUpperCase() + ".jpg") != null) {
				throw new StorageException("Il y a deja une image de ce nom, changer le nom de l'image");
			}
			
			
			modeletelephoneService.enregistrer(modeleCreationDTO);
			storageService.store(file, modeleCreationDTO.getReference().toUpperCase() + ".jpg");
			
			
			return "redirect:/administration/modele/list/"+modeleCreationDTO.getIdMarque();

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}
	
	@GetMapping("/list/{id}")
	public String viewModeles(Model model,@PathVariable long id) {
		
		Marque marque=marqueService.findMarqueById(id);
		List<ModeletelephoneDTO>modeles= modeletelephoneService.findModeletelephoneByMarque(marque);
		model.addAttribute("modeles",modeles);
		model.addAttribute("nomMarque",marque.getNom());
		
		return MODELE_LIST;
	}
	
	@GetMapping("/list")
	public String viewAllModeles(Model model) {
		
		List<Marque>marques= marqueService.lister();
		model.addAttribute("marques",marques);
		
		return MARQUE_LIST;
	}
	
	
	@GetMapping("/modification/{id}")
	public String viewModeleModification(Model model,@PathVariable long id) {
		
		Optional<ModeletelephoneDTO> modeleDTO=modeletelephoneService.findModeletelephoneById(id);
		
		if(modeleDTO.isPresent())
		model.addAttribute("modeleDTO",modeleDTO.get());
		
		return MODELE_UPDATE_FORM;
	
	}
	
	@PostMapping("/modification/{id}")
	public String updateModele(@Valid @ModelAttribute("modeleDTO") ModeletelephoneDTO modeleDTO,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, @RequestParam String modeleVisible) {

		try {

			if (bindingResult.hasErrors())
				return MODELE_UPDATE_FORM;
			
			modeleDTO.setVisibleValue(modeleVisible);
			modeletelephoneService.updateModeletelephone(modeleDTO);
			return "redirect:/administration/modele/list/"+modeleDTO.getIdMarque();

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}
	
	@GetMapping("/suppression/{id}")
	public String deleteModele(Model model,@PathVariable long id) {
		
		Optional<ModeletelephoneDTO> modeleDTO=modeletelephoneService.findModeletelephoneById(id);
		
		if(modeleDTO.isPresent())
			modeletelephoneService.deleteModeletelephone(modeleDTO.get());
		
		return "redirect:/administration/modele/list/"+modeleDTO.get().getIdMarque();
	
	}

}
