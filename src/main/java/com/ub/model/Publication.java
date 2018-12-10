package com.ub.model;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Publication implements Comparable<Publication> {
	
	private int i = 0;

	@Id
	@GeneratedValue
	@Column(name = "Publication_Id", nullable = false)
	private long id;
	
	@Column(name = "Comments", nullable = true)
	private Hashtable<String, List<String>> comments = new Hashtable<String, List<String>>();
	
	@Column(name = "Date", nullable=true)
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	@JsonIgnore
	private AppUser user;
	
	@Column(name = "Main_Text", length = 64, nullable = false)
	private String mainText;

	
	public void setUser(AppUser user) {
		this.user = user;
	}
	
	public AppUser getUser() {
		return user;
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
	
	public Hashtable<String, List<String>> getComments() {
		return comments;
	}
	
	public void setComments(Hashtable<String, List<String>> comments) {
		this.comments = comments;
	}
	
	public List<String> getCommentsUser(String email) {
		
		return comments.get(email);
		
	}
	
	public void addComment(String email, String comment) {
		
		List<String> commentUser = null;
		if (comments.containsKey(email)) {
			commentUser = comments.get(email);
		}
		commentUser.add(i+ " "+comment);
		i++;
		comments.put(email,commentUser);
	}
	
	@Override
	public int compareTo(Publication u) {
		if (getDate() == null || u.getDate() == null) {
			return 0;
		}
		return getDate().compareTo(u.getDate());
	}
}
