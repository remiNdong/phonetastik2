package fr.phonetastik.modeletelephone.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.phonetastik.marque.entity.Marque;
import fr.phonetastik.reparation.entity.Reparation;

@Entity
@Table(name = "modeletelephone", schema = "phonetastik")
public class Modeletelephone {

	public Modeletelephone() {
		//this.visible = "TRUE";
		//this.reference = "";
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;

	@Column(unique = true)
	@NotNull
	private String reference;

	@ManyToOne
	@JoinColumn(name = "idmarque")
	private Marque marque;

	@Column
	private String visible;

	@Column
	private String filename;
	
	@Column
	private double prix;
	
	@OneToMany(mappedBy = "modeletelephone",cascade = CascadeType.ALL)
	private Set<Reparation> reparation = new HashSet<Reparation>();

	public Long getId() {
		return id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Boolean isVisible() {
		return Boolean.parseBoolean(visible.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Modeletelephone)) {
			return false;
		}
		Modeletelephone autre = (Modeletelephone) obj;

		return this.getId() == autre.getId();
	}

	public int hashcode() {

		return this.getId().hashCode();
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Set<Reparation> getReparation() {
		return reparation;
	}

	public void setReparation(Set<Reparation> reparation) {
		this.reparation = reparation;
	}



}
