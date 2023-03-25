package fr.phonetastik.modeletelephone.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import fr.phonetastik.marque.dao.MarqueRepository;
import fr.phonetastik.marque.entity.Marque;
import fr.phonetastik.modeletelephone.dao.ModeletelephoneRepository;
import fr.phonetastik.modeletelephone.entity.ModeleCreationDTO;
import fr.phonetastik.modeletelephone.entity.Modeletelephone;
import fr.phonetastik.modeletelephone.entity.ModeletelephoneDTO;

@Service
@Transactional
@Validated
public class ModeletelephoneServiceImpl implements ModeletelephoneService{
	
	@Autowired
	private ModeletelephoneRepository modeletelephoneRepository;
	
	@Autowired
	private MarqueRepository marqueRepository;
	
	@Autowired
	ModeletelephoneMapper modeletelephoneMapper;

	@Transactional
	@Override
	public void enregistrer(ModeleCreationDTO modeleCreationDTO) {
		
		Marque marque=marqueRepository.getReferenceById(modeleCreationDTO.getIdMarque());
		Modeletelephone modeletelephone=modeletelephoneMapper.modeleCreationDTOtoModele(modeleCreationDTO);
		modeletelephone.setMarque(marque);
		
		modeletelephoneRepository.save(modeletelephone);
		 
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<ModeletelephoneDTO>findModeletelephoneById(Long id) {
		
		
		return modeletelephoneRepository.findById(id).map(modeletelephone -> modeletelephoneMapper.modeletomodeleDTO(modeletelephone));
		
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<ModeletelephoneDTO> findModeletelephoneByMarque(Marque marque) {
		
		List<Modeletelephone> modeles = modeletelephoneRepository.findByMarque(marque);
        return modeles.stream().map(modeletelephoneMapper::modeletomodeleDTO).toList();
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<ModeletelephoneDTO> findModeletelephoneByReference(String reference) {
		
		List<Modeletelephone> modeles = modeletelephoneRepository.findByReference(reference);
        return modeles.stream().map(modeletelephoneMapper::modeletomodeleDTO).toList();
		
	}
	
	@Transactional
	@Override
	public void updateModeletelephone(@Valid ModeletelephoneDTO modeleDTO) {
		
		Modeletelephone modeletelephone=modeletelephoneRepository.getReferenceById(modeleDTO.getId());
		modeletelephone.setPrix(modeleDTO.getPrix());
		modeletelephone.setVisible(modeleDTO.getVisibleValue());
		modeletelephoneRepository.save(modeletelephone);
		
	}

	@Transactional
	@Override
	public void deleteModeletelephone(ModeletelephoneDTO modeleDTO) {
		
		Modeletelephone modeletelephone=modeletelephoneRepository.getReferenceById(modeleDTO.getId());
		modeletelephoneRepository.delete(modeletelephone);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<ModeletelephoneDTO> findModeletelephoneVisibleByMarque(Marque marque) {
		List<Modeletelephone> modeles = modeletelephoneRepository.findVisibleByMarque(marque);
        return modeles.stream().map(modeletelephoneMapper::modeletomodeleDTO).toList();
	}


}
