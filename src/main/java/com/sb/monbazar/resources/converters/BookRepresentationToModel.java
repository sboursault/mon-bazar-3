package com.sb.monbazar.resources.converters;

import com.sb.monbazar.resources.representations.Book;

public class BookRepresentationToModel {

	Book source;

	public BookRepresentationToModel(Book source) {
		super();
		this.source = source;
	}

	public static BookRepresentationToModel source(Book source) {
		return new BookRepresentationToModel(source);
	}

	public com.sb.monbazar.core.model.Book convert() {
		return new com.sb.monbazar.core.model.Book()
				.id(source.getId())
				.title(source.getTitle())
				.author(source.getAuthor());
	}
}
