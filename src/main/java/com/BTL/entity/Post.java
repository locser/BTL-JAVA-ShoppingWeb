package com.BTL.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import groovy.transform.Generated;

//	pause deve
@Entity
public class Post implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int postId;
	@Column(columnDefinition = "text not null")
	String titlePost;
	
	@Column(columnDefinition = "text not null")
	String content;
	@Temporal(TemporalType.DATE)
	Date date;
	
	String image;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int postId, String titlePost, String content, Date date, String image) {
		super();
		this.postId = postId;
		this.titlePost = titlePost;
		this.content = content;
		this.date = date;
		this.image = image;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitlePost() {
		return titlePost;
	}

	public void setTitlePost(String titlePost) {
		this.titlePost = titlePost;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
