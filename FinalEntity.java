package com.tritern.evozspecial.entity;

import java.util.List;

public class FinalEntity {

	private String name;

	private String email;

	private String username;

	private List<String> photo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getPhoto() {
		return photo;
	}

	public void setPhoto(List<String> photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "FinalEntity [name=" + name + ", email=" + email + ", username=" + username + ", photo=" + photo + "]";
	}

}
