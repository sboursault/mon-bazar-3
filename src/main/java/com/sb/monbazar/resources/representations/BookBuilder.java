package com.sb.monbazar.resources.representations;

import com.sb.monbazar.utils.Preconditions;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class BookBuilder {

	private com.sb.monbazar.core.model.Book model;
	private URI baseUri;

	public BookBuilder(com.sb.monbazar.core.model.Book model) {
		super();
		this.model = model;
	}

	public BookBuilder baseUri(URI baseUri) {
		this.baseUri = baseUri;
		return this;
	}

	public Book build() {
		Preconditions.checkNotNull(baseUri, "baseUri must be set");

		Book target = new Book();
		target.setId(model.getId());
		target.setTitle(model.getTitle());
		target.setAuthor(model.getAuthor());
		target.setUri(UriBuilder.fromUri(baseUri).
				path("{id}").
				build(model.getId()));
		return target;
	}
}
