package com.ub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Studies {
	
	@Id
    @GeneratedValue    
    @Column(name = "Studies_Id", nullable = false)
	private long id;
    
	@Column(name = "Studies_Title", length = 64, nullable = false)
	private String title;
	
	@Column(name = "Studies_uni", length = 64, nullable = false)
	private String university;
	
	@Column(name = "Studies_Mark", length = 64, nullable = true)
	private float mark;
	
	@Column(name = "Studies_Comment", length = 256, nullable = true)
	private String comment;
	
	@Column(name = "Studies_BYear", length = 64, nullable = false)
	private int beginYear;
	
	@Column(name = "Studies_EYear", length = 64, nullable = false)
	private int endYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private AppUser user;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(int beginYear) {
		this.beginYear = beginYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
	}

}
