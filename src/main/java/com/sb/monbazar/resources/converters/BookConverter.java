package com.sb.monbazar.resources.converters;

import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.resources.representations.Book;

public class BookConverter {

	Book source;

	public BookConverter(Book source) {
		super();
		this.source = source;
	}

	public static BookConverter from(Book source) {
		return new BookConverter(source);
	}

	public Item toItem() {
		return new Item().id(source.getId()).title(source.getTitle())
				.author(source.getAuthor());
	}
}
