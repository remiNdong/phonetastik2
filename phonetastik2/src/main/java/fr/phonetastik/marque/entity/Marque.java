package fr.phonetastik.marque.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.phonetastik.modeletelephone.entity.Modeletelephone;

@Entity
@Table(name = "marque", schema = "phonetastik")
public class Marque {

	public Marque() {
		//this.visible = "TRUE";
	//	this.nom = "";
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String nom;

	@Column
	private String visible;

	@Column(name = "filename")
	private String filename;

	@OneToMany(mappedBy = "marque",cascade = CascadeType.ALL)
	private Set<Modeletelephone> modeles = new HashSet<Modeletelephone>();

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom.toUpperCase();
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String fileName) {
		this.filename = fileName;
	}

	public Set<Modeletelephone> getModeles() {
		return modeles;
	}

	public void setModeles(Set<Modeletelephone> modeles) {
		this.modeles = modeles;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Marque)) {
			return false;
		}
		Marque autre = (Marque) obj;

		return this.getId() == autre.getId();
	}

	public int hashcode() {

		return this.getId().hashCode();
	}

}
