package com.Project.FoodOrganic.Entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Reviews")
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	@Column(name = "comment")
	
	private String comment ;
	@Column(name = "reviewDate")
	private Date review_date ;
	@Column(name = "rate_star")
	private Integer star ;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Reviews(Long id, String comment, Date review_date, Integer star, User user) {
		super();
		this.id = id;
		this.comment = comment;
		this.review_date = review_date;
		this.star = star;
		this.user = user;
	}

	public Reviews() {
		super();
	}

	@Override
	public String toString() {
		return "Reviews [id=" + id + ", comment=" + comment + ", review_date=" + review_date + ", star=" + star
				+ ", user=" + user + "]";
	}

	
}
