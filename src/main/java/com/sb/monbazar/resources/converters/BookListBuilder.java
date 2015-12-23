package com.sb.monbazar.resources.converters;

import java.net.URI;
import java.util.List;

import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.resources.BooksResource;
import com.sb.monbazar.resources.representations.BookList;
import com.sb.monbazar.utils.Preconditions;

import javax.ws.rs.core.UriBuilder;

public class BookListBuilder {

	private List<Item> items;
	private URI baseUri;

	public BookListBuilder(List<Item> items) {
		super();
		this.items = items;
	}

	public static BookListBuilder from(List<Item> items) {
		return new BookListBuilder(items);
	}


	public BookListBuilder baseUri(URI baseUri) {
		this.baseUri = baseUri;
		return this;
	}

	public BookList build() {

		Preconditions.checkNotNull(baseUri, "baseUri must be set");

		BookList targetList = new BookList();
		for (Item item : items) {
			targetList.add(BookBuilder.from(item).baseUri(baseUri).build());
		}
		return targetList;
	}

}
 