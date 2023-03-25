package fr.phonetastik.modeletelephone.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ModeleCreationDTO {

	public ModeleCreationDTO() {

	}

	@NotBlank
	private String reference;

	@NotNull
	private Long idMarque;
	
	@NotNull
	private double prix;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Long getIdMarque() {
		return idMarque;
	}

	public void setIdMarque(Long idMarque) {
		this.idMarque = idMarque;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}
