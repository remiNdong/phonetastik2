package fr.phonetastik.marque.service;

import java.util.List;

import fr.phonetastik.marque.entity.Marque;

public interface MarqueService {

	Marque enregistrer(Marque marque);
	
	
	Marque  findMarqueById(Long id);
	
	List<Marque>  findMarqueByNom(String nom);
	
	List<Marque> lister();
	
	List<Marque> listerMarquesVisible();
	
	
	
}
