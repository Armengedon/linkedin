package com.ub.model;

import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Publication {
	
	private int i = 0;

	@Id
	@GeneratedValue
	@Column(name = "Publication_Id", nullable = false)
	private long id;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private AppUser author;
	
	
	@Column(name = "comments", nullable = true)
	private Hashtable<String, List<String>> comments = new Hashtable<String, List<String>>();
	
	@Column(name = "date", nullable=false)
	private Date date;
	
	
	public void setAuthor(AppUser author) {this.author = author;}
	public AppUser getAuthor() {return author;}
	
	public void setDate(Date date) {this.date = date;}
	public Date getDate() {return date;}
	
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

	
	
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
