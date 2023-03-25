package fr.phonetastik.modeletelephone.service;

import org.springframework.stereotype.Service;

import fr.phonetastik.modeletelephone.entity.ModeleCreationDTO;
import fr.phonetastik.modeletelephone.entity.Modeletelephone;
import fr.phonetastik.modeletelephone.entity.ModeletelephoneDTO;

@Service
public class ModeletelephoneMapper {

	public Modeletelephone modeleCreationDTOtoModele(ModeleCreationDTO modeleCreationDTO) {

		Modeletelephone modeletelephone = new Modeletelephone();

		modeletelephone.setReference(modeleCreationDTO.getReference().toUpperCase());
		modeletelephone.setVisible("TRUE");
		modeletelephone.setFilename(modeleCreationDTO.getReference().toUpperCase() + ".jpg");
		modeletelephone.setPrix(modeleCreationDTO.getPrix());
		return modeletelephone;
	}

	public ModeletelephoneDTO modeletomodeleDTO(Modeletelephone modele) {

		ModeletelephoneDTO modeleDTO = new ModeletelephoneDTO();
		modeleDTO.setId(modele.getId());
		modeleDTO.setReference(modele.getReference());
		modeleDTO.setIdMarque(modele.getMarque().getId());
		modeleDTO.setVisible(isVisible(modele.getVisible()));
		modeleDTO.setFilename(modele.getFilename());
		modeleDTO.setPrix(modele.getPrix());
		modeleDTO.setNomMarque(modele.getMarque().getNom());

		return modeleDTO;

	}

	public boolean isVisible(String valeur) {

		boolean visible = true;

		if (valeur.equals("FALSE"))
			visible = false;

		return visible;
	}

}
