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

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.googlecode.objectify.ObjectifyService;
import static com.sb.monbazar.Manager.*;
import com.sb.monbazar.core.model.*;
import com.sb.monbazar.repositories.BookRepository;
import com.sb.monbazar.resources.converters.*;
import com.sb.monbazar.resources.representations.*;
import com.sb.monbazar.resources.representations.Book;
import com.sb.monbazar.utils.Preconditions;


@Path("books")
public class BooksResource {

	@Context UriInfo uriInfo;

	/*@Inject*/	BookRepository bookRepository = new BookRepository();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BookList getAllBooks() {
		return representationOf(getBookRepository().search());
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Book getBook(@PathParam("id") Long id) {
		return representationOf(getBookRepository().getBook(id));
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response create(Book representation) throws IOException {
		com.sb.monbazar.core.model.Book model = new BookRepresentationToModel(representation).convert();
		model = getBookRepository().saveOrUpdate(model);
		URI uri = representationOf(model).getUri();
		return Response.created(uri).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Book update(Book representation) throws IOException {
		Preconditions.checkNotNull(representation.getId(), "book.id must be set");
		com.sb.monbazar.core.model.Book model = new BookRepresentationToModel(representation).convert();
		model = getBookRepository().saveOrUpdate(model);
		return representationOf(model);
	}

	private URI getBaseUri() {
		return uriInfo.getBaseUriBuilder().path("/books").build();
	}

	private Book representationOf(com.sb.monbazar.core.model.Book model) {
		return new BookBuilder(model)
				.baseUri(getBaseUri())
				.build();
	}

	private BookList representationOf(List<com.sb.monbazar.core.model.Book> models) {
		return new BookListBuilder(models)
				.baseUri(getBaseUri())
				.build();
	}

}
