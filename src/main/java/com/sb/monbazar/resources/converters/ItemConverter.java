package com.sb.monbazar.resources.converters;

import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.resources.representations.Book;

public class ItemConverter {

	Item item;

	public ItemConverter(Item item) {
		super();
		this.item = item;
	}

	public static ItemConverter from(Item item) {
		return new ItemConverter(item);
	}

	public Book toBook() {
		Book target = new Book();
		target.setId(item.getId());
		target.setTitle(item.getTitle());
		target.setAuthor(item.getAuthor());
		return target;
	}
}
