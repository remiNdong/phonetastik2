package fr.phonetastik.reparation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.phonetastik.modeletelephone.entity.Modeletelephone;
import fr.phonetastik.reparation.entity.Reparation;

public interface ReparationRepository  extends JpaRepository<Reparation, Long> {
	
	public List<Reparation> findByModeletelephoneAndNom(Modeletelephone modele, String nom);
	public List<Reparation> findByModeletelephone(Modeletelephone modele);
	
	@Query("select r from Reparation  r , Modeletelephone mo where mo=r.modeletelephone and mo=:modele and r.visible='TRUE'")
	public List<Reparation> findVisibleByModeletelephone(Modeletelephone modele);

}
