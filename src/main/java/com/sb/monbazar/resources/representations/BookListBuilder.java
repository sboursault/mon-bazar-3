package com.sb.monbazar.resources.representations;

import java.net.URI;
import java.util.List;

import com.sb.monbazar.core.model.*;
import com.sb.monbazar.utils.Preconditions;

public class BookListBuilder {

	private List<com.sb.monbazar.core.model.Book> models;
	private URI baseUri;

	public BookListBuilder(List<com.sb.monbazar.core.model.Book> models) {
		super();
		this.models = models;
	}

	public BookListBuilder baseUri(URI baseUri) {
		this.baseUri = baseUri;
		return this;
	}

	public BookList convert() {

		Preconditions.checkNotNull(baseUri, "baseUri must be set");

		BookList targetList = new BookList();
		for (com.sb.monbazar.core.model.Book item : models) {
			targetList.add(new BookBuilder(item).baseUri(baseUri).build());
		}
		return targetList;
	}

}
 