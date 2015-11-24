package com.sb.monbazar.core.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
public class Item {

	@Id
	private Long id;

	private String user;

	private String title = "";
	private String author = "";
	private String owner = "";

	private Date creationDate;
	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Item id(Long id) {
		this.setId(id);
		return this;
	}

	public Item user(String user) {
		this.setUser(user);
		return this;
	}

	public Item title(String title) {
		this.setTitle(title);
		return this;
	}

	public Item author(String author) {
		this.setAuthor(author);
		return this;
	}

	public Item owner(String owner) {
		this.setOwner(owner);
		return this;
	}

}
