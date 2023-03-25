package fr.phonetastik.reparation.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReparationDTO {

	
	@NotNull
	private Long id;

	@NotBlank
	private String nom;
	
	@NotBlank
	private String nomModele;

	@NotNull
	private Long idModele;
	
	@NotNull
	private boolean visible;

	

	@NotNull
	private double prix;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
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

	public String getVisibleValue() {

		if (this.isVisible())
			return "TRUE";
		else
			return "FALSE";

	}
	public void setVisibleValue(String value) {
		

		if(value.equals("TRUE"))
			this.setVisible(true);
		else
			this.setVisible(false);
	

	}
	
}
