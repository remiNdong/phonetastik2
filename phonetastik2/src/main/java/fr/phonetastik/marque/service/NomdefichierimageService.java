package fr.phonetastik.marque.service;

import fr.phonetastik.marque.entity.Nomdefichierimage;

public interface NomdefichierimageService {
	
	Nomdefichierimage enregistrer (Nomdefichierimage nomdefichierimage);
	
	Nomdefichierimage  findNomDeFichierImageById(String id);
	
	

}
