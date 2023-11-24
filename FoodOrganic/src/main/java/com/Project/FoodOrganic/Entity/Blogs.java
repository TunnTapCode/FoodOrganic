package com.Project.FoodOrganic.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Blogs")
public class Blogs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;
	private Date createdDate;
	
	@Column(name = "tags" )
	private String tags ;
	@Column(name = "image" )
	private String image ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Blogs(Long id, String title, String content, Date createdDate, String tags) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdDate = createdDate;
		this.tags = tags;
	}
	public Blogs() {
		super();
	}
	@Override
	public String toString() {
		return "Blogs [id=" + id + ", title=" + title + ", content=" + content + ", createdDate=" + createdDate
				+ ", tags=" + tags + ", image=" + image + "]";
	}
	
	
	
}
