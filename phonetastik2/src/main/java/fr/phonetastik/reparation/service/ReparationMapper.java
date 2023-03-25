package fr.phonetastik.reparation.service;

import org.springframework.stereotype.Service;

import fr.phonetastik.reparation.entity.Reparation;
import fr.phonetastik.reparation.entity.ReparationCreationDTO;
import fr.phonetastik.reparation.entity.ReparationDTO;

@Service
public class ReparationMapper {
	
	public Reparation reparationCreationDTOtoreparation(ReparationCreationDTO reparationCreationDTO) {

		Reparation reparation = new Reparation();

		reparation.setNom(reparationCreationDTO.getNom().toUpperCase());
		reparation.setPrix(reparationCreationDTO.getPrix());
		reparation.setVisible("TRUE");
		
		return reparation;
	}
	
	public ReparationDTO reparationToReparationDTO (Reparation reparation) {

		ReparationDTO reparationDTO = new ReparationDTO();
		reparationDTO.setId(reparation.getId());
		reparationDTO.setNom(reparation.getNom());
		reparationDTO.setIdModele(reparation.getModeletelephone().getId());
		reparationDTO.setNomModele(reparation.getModeletelephone().getReference());
		reparationDTO.setVisible(isVisible(reparation.getVisible()));
		reparationDTO.setPrix(reparation.getPrix());
		reparationDTO.setNomModele(reparation.getModeletelephone().getReference());

		return reparationDTO;

	}

	public boolean isVisible(String valeur) {

		boolean visible = true;

		if (valeur.equals("FALSE"))
			visible = false;

		return visible;
	}

}
