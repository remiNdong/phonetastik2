package fr.phonetastik.modeletelephone.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ModeletelephoneDTO {

	@NotNull
	private Long id;

	@NotBlank
	private String reference;

	@NotNull
	private Long idMarque;

	@NotBlank
	private String nomMarque;

	@NotNull
	private boolean visible;

	@NotBlank
	private String filename;

	@NotNull
	private double prix;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
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

	public String getNomMarque() {
		return nomMarque;
	}

	public void setNomMarque(String nomMarque) {
		this.nomMarque = nomMarque;
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
