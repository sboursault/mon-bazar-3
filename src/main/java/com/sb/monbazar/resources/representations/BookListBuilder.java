package com.sb.monbazar.resources.representations;

import java.net.URI;
import java.util.List;

import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.utils.Preconditions;

public class BookListBuilder {

	private List<Item> items;
	private URI baseUri;

	public BookListBuilder(List<Item> items) {
		super();
		this.items = items;
	}

	public BookListBuilder baseUri(URI baseUri) {
		this.baseUri = baseUri;
		return this;
	}

	public BookList convert() {

		Preconditions.checkNotNull(baseUri, "baseUri must be set");

		BookList targetList = new BookList();
		for (Item item : items) {
			targetList.add(new BookBuilder(item).baseUri(baseUri).build());
		}
		return targetList;
	}

}
 