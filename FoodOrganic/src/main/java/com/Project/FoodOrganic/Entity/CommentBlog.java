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
@Table(name = "comment_blogs")
public class CommentBlog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	private Date date_comment ;
	@Column(columnDefinition = "text") 
	private String comment ;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user ;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "blog_id")
	private Blogs blogs ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate_comment() {
		return date_comment;
	}

	public void setDate_comment(Date date_comment) {
		this.date_comment = date_comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Blogs getBlogs() {
		return blogs;
	}

	public void setBlogs(Blogs blogs) {
		this.blogs = blogs;
	}

	public CommentBlog(Long id, Date date_comment, String comment, User user, Blogs blogs) {
		super();
		this.id = id;
		this.date_comment = date_comment;
		this.comment = comment;
		this.user = user;
		this.blogs = blogs;
	}

	public CommentBlog() {
		super();
	}

	@Override
	public String toString() {
		return "CommentBlog [id=" + id + ", date_comment=" + date_comment + ", comment=" + comment + ", user=" + user
				+ ", blogs=" + blogs + "]";
	}
	
	
	
	

}
