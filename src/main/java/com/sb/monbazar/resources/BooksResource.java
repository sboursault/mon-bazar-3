/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2010-2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.sb.monbazar.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.googlecode.objectify.ObjectifyService;
import com.sb.monbazar.controlers.OfyHelper;
import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.resources.converters.ItemConverter;
import com.sb.monbazar.resources.converters.ItemListConverter;
import com.sb.monbazar.resources.representations.Book;
import com.sb.monbazar.resources.representations.BookSummary;

import static com.google.common.base.Preconditions.checkArgument;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello

// from www.vogella.com/tutorials/REST/article.html
@Path("books")
public class BooksResource {

	
//	  @GET @Path("{id}")
//	  String getWidget(@PathParam("id") String id) {...}
	
	public final static String PATH = "/api/books/";
	
	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String text() {
		return Joiner.on(", ").join(getBookList());
	}

	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<BookSummary> list() {
		return getBookList();
	}


	// This method is called if XML is request
//	@GET
//	@Produces(MediaType.TEXT_XML)
//	public String sayXMLHello() {
//		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
//	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String html() {
		return "<html> " + "<title>books</title>"
				+ "<body>" + text() + "</body>" + "</html> ";
	}

	@GET
	@Path("{id}")
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
	
	private List<BookSummary> getBookList() {
//		List<Item> items = Dao.INSTANCE.getItemsFromUser(Dao.SEB);
		List<Item> items = ObjectifyService.ofy().load().type(Item.class).limit(50).list();
		return ItemListConverter.from(items).toBookList();

	}

}
