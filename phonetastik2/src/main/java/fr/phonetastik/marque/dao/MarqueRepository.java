package fr.phonetastik.marque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.phonetastik.marque.entity.Marque;

public interface MarqueRepository extends JpaRepository<Marque, Long> {
	
	
	public List<Marque> findByNom(String nom);
	
	

	
	
	@Query("select m from Marque m where m.visible='TRUE'")
	public List<Marque> findMarquesVisibles();


}