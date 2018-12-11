package com.ub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment implements Comparable<Comment> {
	
	@Id
	@GeneratedValue
	@Column(name = "Comment_Id", nullable = false)
	private long id;
	
	@Column(name = "Date", nullable=true)
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id")
	@JsonIgnore
	private Publication publication;
	
	@Column(name = "Main_Text", length = 64, nullable = false)
	private String mainText;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	@JsonIgnore
	private AppUser user;

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
	public Publication getPublication() {
		return publication;
	}
	
	public String getMainText() {
		return mainText;
	}
	
	public void setMainText(String mainText) {
		this.mainText = mainText;
	}
	
	public void setDate(Date date) {
		this.date = date;
	
	}
	
	public Date getDate() {
		return date;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(Comment u) {
		if (getDate() == null || u.getDate() == null) {
			return 0;
		}
		return getDate().compareTo(u.getDate());
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}
}
