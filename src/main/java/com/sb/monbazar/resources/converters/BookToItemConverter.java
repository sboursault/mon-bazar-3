package com.sb.monbazar.resources.converters;

import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.resources.representations.Book;

public class BookToItemConverter {

	Book source;

	public BookToItemConverter(Book source) {
		super();
		this.source = source;
	}

	public static BookToItemConverter source(Book source) {
		return new BookToItemConverter(source);
	}

	public Item convert() {
		return new Item()
				.id(source.getId())
				.title(source.getTitle())
				.author(source.getAuthor());
	}
}
