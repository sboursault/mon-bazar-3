// CHECKSTYLE:OFF
/**
 * Source code generated by Fluent Builders Generator
 * Do not modify this file
 * See generator home page at: http://code.google.com/p/fluent-builders-generator-eclipse-plugin/
 */

package com.sb.monbazar.core.model;

import java.util.Date;

public class ItemBuilder extends ItemBuilderBase<ItemBuilder> {
	public static ItemBuilder anItem() {
		return new ItemBuilder();
	}

	public ItemBuilder() {
		super(new Item());
	}

	public Item build() {
		return getInstance();
	}
}

class ItemBuilderBase<GeneratorT extends ItemBuilderBase<GeneratorT>> {
	private Item instance;

	protected ItemBuilderBase(Item aInstance) {
		instance = aInstance;
	}

	protected Item getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT id(Long id) {
		instance.setId(id);
		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT user(String user) {
		instance.setUser(user);
		return (GeneratorT) this;
	}
	
	@SuppressWarnings("unchecked")
	public GeneratorT title(String title) {
		instance.setTitle(title);
		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT author(String author) {
		instance.setAuthor(author);
		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT owner(String owner) {
		instance.setOwner(owner);
		return (GeneratorT) this;
	}
	
	

	@SuppressWarnings("unchecked")
	public GeneratorT creationDate(Date creationDate) {
		instance.setCreationDate(creationDate);
		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT updateDate(Date updateDate) {
		instance.setUpdateDate(updateDate);
		return (GeneratorT) this;
	}
}
