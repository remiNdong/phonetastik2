package fr.phonetastik.authentification;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user",schema = "phonetastik")
public class User implements Serializable {
	
	public User() {
		admin="FALSE";
	}

	@Id
	private String email;

	@Column(name="pseudo")
	private String pseudo;

	@Column(name="password")
	private String password;
	
	@Column(name="admin")
	private String admin;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email= email;
	}
	

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	
	public Boolean isAdmin() {
		return Boolean.parseBoolean(admin.toLowerCase());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User autre = (User) obj;

		return this.getEmail().equals(autre.getEmail());
	}
	

	public String toString() {

		return "User [email=" + email + ", pseudo=" + pseudo + "]";
	}


	

}
