package fr.phonetastik.reparation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.phonetastik.modeletelephone.entity.Modeletelephone;

@Entity
@Table(name = "reparation", schema = "phonetastik")
public class Reparation {
	
	
	public Reparation() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	
	@Column
	@NotNull
	private String nom;
	
	@Column
	private double prix;
	
	@ManyToOne
	@JoinColumn(name = "idmodele")
	private Modeletelephone modeletelephone;
	
	@Column
	private String visible;
	


	public Long getId() {
		return id;
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Modeletelephone getModeletelephone() {
		return modeletelephone;
	}

	public void setModeletelephone(Modeletelephone modele) {
		this.modeletelephone = modele;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	
	
	public Boolean isVisible() {
		return Boolean.parseBoolean(visible.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Reparation)) {
			return false;
		}
		Reparation autre = (Reparation) obj;

		return this.getId() == autre.getId();
	}

	public int hashcode() {

		return this.getId().hashCode();
	}

}
