package fr.phonetastik.modeletelephone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.phonetastik.marque.entity.Marque;
import fr.phonetastik.modeletelephone.entity.Modeletelephone;

public interface ModeletelephoneRepository extends JpaRepository<Modeletelephone, Long> {
	
	
	public List<Modeletelephone> findByMarque(Marque marque);
	
	@Query("select mo from Marque m , Modeletelephone mo where m=mo.marque and m=:marque and mo.visible='TRUE'")
	public List<Modeletelephone> findVisibleByMarque(Marque marque);
	
	public List<Modeletelephone> findByReference(String reference);
	
	
}