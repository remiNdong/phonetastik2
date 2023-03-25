package fr.phonetastik.marque.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="nomdefichierimage",schema = "phonetastik")
public class Nomdefichierimage {

	public Nomdefichierimage() {

	}

	public Nomdefichierimage(String filename) {
		this.filename = filename;
	}

	@Id
	@NotNull
	private String filename;

	public String getFilename() {
		return filename;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Nomdefichierimage)) {
			return false;
		}
		Nomdefichierimage autre = (Nomdefichierimage) obj;

		return this.getFilename().equals(autre.getFilename());
	}
	
public int hashcode() {
		
		return this.getFilename().hashCode();
	}

}
