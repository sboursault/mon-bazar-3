package com.sb.monbazar.resources.representations;

import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.utils.Preconditions;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class BookBuilder {

	private  Item item;
	private URI baseUri;

	public BookBuilder(Item item) {
		super();
		this.item = item;
	}

	public BookBuilder baseUri(URI baseUri) {
		this.baseUri = baseUri;
		return this;
	}

	public Book build() {
		Preconditions.checkNotNull(baseUri, "baseUri must be set");

		Book target = new Book();
		target.setId(item.getId());
		target.setTitle(item.getTitle());
		target.setAuthor(item.getAuthor());
		target.setUri(UriBuilder.fromUri(baseUri).
				path("{id}").
				build(item.getId()));
		return target;
	}
}
