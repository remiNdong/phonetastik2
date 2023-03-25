package fr.phonetastik.reparation.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReparationCreationDTO {
	

	
	@NotBlank
	private String nom;
	
	@NotNull
	private Long idModele;
	
	@NotNull
	private String nomModele;
	
	@NotNull
	private double prix;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getIdModele() {
		return idModele;
	}

	public void setIdModele(Long idModele) {
		this.idModele = idModele;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getNomModele() {
		return nomModele;
	}

	public void setNomModele(String nomModele) {
		this.nomModele = nomModele;
	}

}
