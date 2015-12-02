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
import java.net.URI;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.resources.converters.*;
import com.sb.monbazar.resources.representations.*;
import com.sb.monbazar.utils.Preconditions;


@Path("books")
public class BooksResource {

	public final static String PATH = "/api/books/";

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BookList getAllBooks() {
		List<Item> items = ObjectifyService.ofy().load().type(Item.class).limit(50).list();
		return ItemListConverter.from(items).toBookList();
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Book getBook(@PathParam("id") Long id) {
		Item item = ObjectifyService.ofy().load().type(Item.class).id(id).now();
		return ItemConverter.from(item).toBook();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response create(Book entity, @Context UriInfo uriInfo) throws IOException {
		Preconditions.checkNotBlank(entity.getTitle(), "invalid property : book.title");
		Book book = saveBook(entity);
		URI newUri = URI.create(uriInfo.getPath() + "/" + book.getId());
		return Response.created(newUri).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Book update(Book entity) throws IOException {
		Preconditions.checkNotNull(entity.getId(), "invalid property : book.id");
		Preconditions.checkNotBlank(entity.getTitle(), "invalid property : book.title");
		return saveBook(entity);
	}

	private Book saveBook(Book entity) {
		Item item = BookConverter.from(entity).toItem();
		Key<Item> key = ObjectifyService.ofy().save().entity(item).now();
		return getBook(key.getId());
	}
}
