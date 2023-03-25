package fr.phonetastik.marque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.phonetastik.marque.dao.MarqueRepository;
import fr.phonetastik.marque.entity.Marque;

@Service
public class MarqueServiceImpl implements MarqueService {

	@Autowired
	private MarqueRepository marqueRepository;

	@Transactional
	@Override
	public Marque enregistrer(Marque marque) {
		return marqueRepository.save(marque);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Marque> lister() {
		return (List<Marque>) marqueRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Marque findMarqueById(Long id) {
		return marqueRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Marque> findMarqueByNom(String nom) {
		return marqueRepository.findByNom(nom);
	}
	



	@Transactional(readOnly = true)
	@Override
	public List<Marque> listerMarquesVisible() {
		return  marqueRepository.findMarquesVisibles();
}
	
}

