package com.sb.monbazar.resources;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Preconditions;
import com.googlecode.objectify.ObjectifyService;
import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.resources.converters.ItemConverter;
import com.sb.monbazar.resources.representations.Book;

@Path("book")
public class BookResource {

	public final static String PATH = "/rest/book/";

	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String text(@PathParam("id") Long id) {
		checkArgument(id != null, "missing parameter : id");
		return getBook(id).toString();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Book get(@PathParam("id") Long id) {
		checkArgument(id != null, "missing parameter : id");
		return getBook(id);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String html(@PathParam("id") Long id) {
		checkArgument(id != null, "missing parameter : id");
		return "<html> " + "<title>books</title>" + "<body>" + text(id)
				+ "</body>" + "</html> ";
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Book create(Book entity) throws IOException {
		checkNotBlank(entity.getTitle(), "invalid property : book.title");
		return saveBook(entity);
	}
	
	@POST
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Book update(Book entity) throws IOException {
		checkNotBlank(entity.getTitle(), "invalid property : book.title");
		return saveBook(entity);
	}

	private Book saveBook(Book entity) {
//		Item item = BookConverter.from(entity).toItem().user(Dao.SEB);
//		Item persisted = Dao.INSTANCE.saveOrUpdate(item);
//		return ItemConverter.from(persisted).toBook();
		throw new UnsupportedOperationException();
	}

	private void checkNotBlank(String value, String msg) {
		Preconditions.checkArgument(value != null && !value.trim().equals(""), msg);
	}

	private Book getBook(Long id) {
//		Item item = Dao.INSTANCE.getItem(id);
		Item item = ObjectifyService.ofy().load().type(Item.class).id(id).now();
		return ItemConverter.from(item).toBook();
	}

}
