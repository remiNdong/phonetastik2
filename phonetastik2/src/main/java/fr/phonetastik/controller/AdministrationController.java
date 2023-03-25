package fr.phonetastik.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.phonetastik.marque.entity.Marque;
import fr.phonetastik.marque.entity.Nomdefichierimage;
import fr.phonetastik.marque.service.MarqueService;
import fr.phonetastik.marque.service.NomdefichierimageService;
import fr.phonetastik.utils.StorageException;
import fr.phonetastik.utils.StorageService;

@Controller
public class AdministrationController {

	@Autowired
	StorageService storageService;

	@Autowired
	NomdefichierimageService nomdefichierimageService;

	@Autowired
	MarqueService marqueService;

	@GetMapping(value = "/administration")
	public String viewTemplate(Model model) {
		return "administration";
	}

	@GetMapping(value = "/administration/creationMarque")
	public String viewTemplateCreation(@Valid Marque marque, Model model) {

		try {

			return "creationMarque";
		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}
	}

	@GetMapping(value = "/administration/modificationMarque")
	public String viewTemplateModification(Model model) {

		try {

			return "redirect:/listeMarques2";
		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}
	}

	@GetMapping(value = "/administration/modificationMarque/{id}")
	public String viewTemplateModificationNumero(Model model, @PathVariable long id) {

		try {

			Marque marque = marqueService.findMarqueById(id);
			model.addAttribute("marque", marque);
			return "modificationMarque";
		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}
	}

	@PostMapping(value = "/administration/modificationMarque/{id}")
	public String viewTemplateModificationNumeroPost(Model model, @PathVariable long id, HttpServletRequest request) {
		try {

			Marque marque = marqueService.findMarqueById(id);
			model.addAttribute("marque", marque);

			String visible = request.getParameter("visible");
			marque.setVisible(visible);

			marqueService.enregistrer(marque);
			return "redirect:/listeMarques2";

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}
	}

	@GetMapping(value = "listeMarques2")
	public String viewTemplateListeMarques(Model model) {

		// model.addAttribute("map", storageService.getMap());

		try {

			model.addAttribute("listeMarques", marqueService.lister());
			return "listeMarques2";

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}

	@PostMapping(value = "/administration/creationMarque")
	public String viewTemplateCreationPost(@RequestParam("imageMarque") MultipartFile file, @Valid Marque marque,
			Model model) {

		try {

			/*
			 * if
			 * (nomdefichierimageService.findNomDeFichierImageById(file.getOriginalFilename(
			 * )) != null) { throw new
			 * StorageException("Il y a deja une image de ce nom, changer le nom de l'image"
			 * ); }
			 */

			if (nomdefichierimageService.findNomDeFichierImageById(marque.getNom().toUpperCase() + ".jpg") != null) {
				throw new StorageException("Il y a deja une image de ce nom, changer le nom de l'image");
			}

			if (marqueService.findMarqueByNom(marque.getNom().toUpperCase()).isEmpty()) {

				storageService.store(file, marque.getNom().toUpperCase() + ".jpg");

				Nomdefichierimage nomdefichierimage = new Nomdefichierimage(marque.getNom().toUpperCase() + ".jpg");
				nomdefichierimageService.enregistrer(nomdefichierimage);

				marque.setFilename(marque.getNom().toUpperCase() + ".jpg");
				marque.setVisible("TRUE");
				String nomMarque=marque.getNom();
				marque.setNom(nomMarque.toUpperCase());
				marqueService.enregistrer(marque);
				return "redirect:/listeMarques2";

			} else {
				model.addAttribute("erreurCreationMarque",
						"Il ne peut y avoir qu'une marque de  nom " + marque.getNom());
				return "redirect:/creationMarque";
			}

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}

}
