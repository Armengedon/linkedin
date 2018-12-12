package com.ub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PhotoUser {
	
	@Id
    @GeneratedValue    
    @Column(name = "Picture_ID", nullable = false)
	private long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	@JsonIgnore
	private AppUser user;
	
	@Column(name = "File_Name", nullable = true)
	private String filename;
	
	@Lob
	private String data;
	
	public PhotoUser() {
	}
	
	public PhotoUser(String filename,String string) {
		this.filename = filename;
		this.data = string;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
