package fr.phonetastik.modeletelephone.service;

import java.util.List;
import java.util.Optional;

import fr.phonetastik.marque.entity.Marque;
import fr.phonetastik.modeletelephone.entity.ModeleCreationDTO;
import fr.phonetastik.modeletelephone.entity.ModeletelephoneDTO;

public interface ModeletelephoneService {
	
	void enregistrer(ModeleCreationDTO modeleCreationDTO);
	
	
	Optional<ModeletelephoneDTO>  findModeletelephoneById(Long id);
	
	List<ModeletelephoneDTO>  findModeletelephoneByMarque(Marque marque);
	
	List<ModeletelephoneDTO>  findModeletelephoneVisibleByMarque(Marque marque);
	
	List<ModeletelephoneDTO>  findModeletelephoneByReference(String reference);
	
	void updateModeletelephone(ModeletelephoneDTO modeleDTO);
	
	void deleteModeletelephone(ModeletelephoneDTO modeleDTO);

}
