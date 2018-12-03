package com.ub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Picture {
	
	
	public Picture() {
		
	}
	
	@Id
    @GeneratedValue    
    @Column(name = "Picture_id", nullable = false)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private AppUser user;
	
	/* maximum 10Mb */
	@Column(name = "Picture_Byte", nullable = false, length = 1073741824)
	private byte[] pictureFile;
	
	@Column(name = "original_name", nullable = false, length = 127)
	private String originalFileName;
		
	@Column(name = "filesize", nullable = false)
	private int fileSize;
	
	public Picture(String originalFileName, String pictureTitle, byte[] pictureFile) {
		super();
		this.originalFileName = originalFileName;
		this.pictureFile = pictureFile;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public byte[] getPictureFile() {
		return pictureFile;
	}

	public void setPictureFile(byte[] pictureFile) {
		this.pictureFile = pictureFile;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	
	
	

}
