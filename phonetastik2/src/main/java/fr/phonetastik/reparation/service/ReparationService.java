package fr.phonetastik.reparation.service;

import java.util.List;
import java.util.Optional;

import fr.phonetastik.modeletelephone.entity.ModeletelephoneDTO;
import fr.phonetastik.reparation.entity.ReparationCreationDTO;
import fr.phonetastik.reparation.entity.ReparationDTO;

public interface ReparationService {
	
void enregistrer(ReparationCreationDTO reparationCreationDTO);
	
	
	Optional<ReparationDTO>  findReparationById(Long id);
	
	List<ReparationDTO>  findReparationByModele(ModeletelephoneDTO modeleDTO);
	
	List<ReparationDTO>  findModeleByModeleAndNom(Long idModele , String nom);
	
void updateReparation(ReparationDTO reparationDTO);
	
	void deleteReparation(ReparationDTO reparationDTO);


	List<ReparationDTO> findReparationVisibleByModele(ModeletelephoneDTO modeleDTO);

}
