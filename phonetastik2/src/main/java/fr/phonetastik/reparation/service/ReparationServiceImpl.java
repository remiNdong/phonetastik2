package fr.phonetastik.reparation.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import fr.phonetastik.modeletelephone.dao.ModeletelephoneRepository;
import fr.phonetastik.modeletelephone.entity.Modeletelephone;
import fr.phonetastik.modeletelephone.entity.ModeletelephoneDTO;
import fr.phonetastik.reparation.dao.ReparationRepository;
import fr.phonetastik.reparation.entity.Reparation;
import fr.phonetastik.reparation.entity.ReparationCreationDTO;
import fr.phonetastik.reparation.entity.ReparationDTO;

@Service
@Transactional
@Validated
public class ReparationServiceImpl implements ReparationService {

	@Autowired
	ReparationRepository reparationRepository;

	@Autowired
	ModeletelephoneRepository modeletelephoneRepository;

	@Autowired
	ReparationMapper reparationMapper;

	@Transactional
	@Override
	public void enregistrer(ReparationCreationDTO reparationCreationDTO) {

		Modeletelephone modele = modeletelephoneRepository.getReferenceById(reparationCreationDTO.getIdModele());
		Reparation reparation=reparationMapper.reparationCreationDTOtoreparation(reparationCreationDTO);
		reparation.setModeletelephone(modele);
		reparationRepository.save(reparation);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<ReparationDTO> findReparationById(Long id) {
		return reparationRepository.findById(id).map(reparation -> reparationMapper.reparationToReparationDTO(reparation));
	}

	@Transactional(readOnly = true)
	@Override
	public List<ReparationDTO> findReparationByModele(ModeletelephoneDTO modeleDTO) {
		Modeletelephone modele = modeletelephoneRepository.getReferenceById(modeleDTO.getId());
		List<Reparation> reparations=reparationRepository.findByModeletelephone(modele);
		return reparations.stream().map(reparationMapper::reparationToReparationDTO).toList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<ReparationDTO> findModeleByModeleAndNom(Long idModele, String nom) {

		Modeletelephone modele = modeletelephoneRepository.getReferenceById(idModele);
		List<Reparation> reparations = reparationRepository.findByModeletelephoneAndNom(modele, nom);
		return reparations.stream().map(reparationMapper::reparationToReparationDTO).toList();
	}
	
	@Transactional
	@Override
	public void updateReparation(@Valid ReparationDTO reparationDTO) {
		
		Reparation reparation=reparationRepository.getReferenceById(reparationDTO.getId());
		reparation.setPrix(reparationDTO.getPrix());
		reparation.setVisible(reparationDTO.getVisibleValue());
		reparationRepository.save(reparation);
		
	}

	@Transactional
	@Override
	public void deleteReparation(ReparationDTO reparationDTO) {
		
		Reparation reparation=reparationRepository.getReferenceById(reparationDTO.getId());
		reparationRepository.delete(reparation);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<ReparationDTO> findReparationVisibleByModele(ModeletelephoneDTO modeleDTO) {
		Modeletelephone modele = modeletelephoneRepository.getReferenceById(modeleDTO.getId());
		List<Reparation> reparations=reparationRepository.findVisibleByModeletelephone(modele);
		return reparations.stream().map(reparationMapper::reparationToReparationDTO).toList();
	}

}
