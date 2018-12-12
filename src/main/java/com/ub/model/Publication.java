package com.ub.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Publication implements Comparable<Publication> {
	
	@Id
	@GeneratedValue
	@Column(name = "Publication_Id", nullable = false)
	private long id;
	
	@Column(name = "Date", nullable=true)
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	@JsonIgnore
	private AppUser user;
	
	@Column(name = "Main_Text", length = 64, nullable = false)
	private String mainText;
	
	@OneToMany(mappedBy = "publication")
    private List<Comment> comments_list = new ArrayList<>();
	
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
	
	public List<Comment> getSortedComments(){
		
		ArrayList<Comment> sorted = new ArrayList<Comment>(comments_list);
		
		Collections.sort(sorted);
		Collections.reverse(sorted);
		
		return sorted;
	}
	
	public void addComment(Comment comment) {
		this.comments_list.add(comment);
		comment.setPublication(this);
	}
	
	@Override
	public int compareTo(Publication u) {
		if (getDate() == null || u.getDate() == null) {
			return 0;
		}
		return getDate().compareTo(u.getDate());
	}
	
}
