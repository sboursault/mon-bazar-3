package com.sb.monbazar.resources;

import com.google.common.collect.Lists;
import com.sb.monbazar.Manager;
import com.sb.monbazar.core.model.Book;
import com.sb.monbazar.repositories.BookRepository;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.JerseyTest;

import static com.sb.monbazar.matchers.jersey.ClientResponseMatchers.*;
import static org.junit.Assert.*;


import com.sun.jersey.test.framework.WebAppDescriptor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.ReturnsArgumentAt;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.ws.rs.core.MediaType;

public class BooksResourceTest extends JerseyTest {

    public BooksResourceTest() {
	    // the WebAppDescriptor should follow the definition from the web.xml
        super(new WebAppDescriptor.Builder("com.sb.monbazar.resources")
                .initParam("com.sun.jersey.spi.container.ContainerResponseFilters",
                        "com.sb.monbazar.resources.responsefilters.CrossOriginResourceSharingResponseFilter")
                .build());
    }

    @Mock
    BookRepository bookRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Manager.setBookRepository(bookRepository);
    }

    @Test
    public void fetchABookList() throws Exception {

        // given
        when(bookRepository.search()).thenReturn(Lists.newArrayList(
                new Book().id(1000).author("Ennis").title("Hellblazer"),
                new Book().id(1005).author("Pratt").title("Tango")
        ));

        // when
        ClientResponse response = resource()
                .path("books")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        // then
        String expected = "" +
                "{\"books\":[" +
                "    {\"id\":\"1000\"," +
                "        \"author\":\"Ennis\"," +
                "        \"title\":\"Hellblazer\"," +
                "        \"uri\":\"http://localhost:9998/books/1000\"}," +
                "    {\"id\":\"1005\"," +
                "        \"author\":\"Pratt\"," +
                "        \"title\":\"Tango\"," +
                "        \"uri\":\"http://localhost:9998/books/1005\"}" +
                "]}";

        assertThat(response, hasStatus(200));
        assertThat(response, hasHeader("Access-Control-Allow-Origin", "*"));
        assertThat(response, hasJsonBody(expected));
    }

    @Test
    public void fetchABookUsingJson() throws Exception {

        // given
        when(bookRepository.getBook(1000)).thenReturn(
                new Book().id(1000).author("Ennis").title("Hellblazer")
        );

        // when
        ClientResponse response = resource()
                .path("books/1000")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        // then
        String expected = "" +
                "{\"id\":\"1000\"," +
                "    \"author\":\"Ennis\"," +
                "    \"title\":\"Hellblazer\"," +
                "    \"uri\":\"http://localhost:9998/books/1000\"}";

        assertThat(response, hasStatus(200));
        assertThat(response, hasHeader("Access-Control-Allow-Origin", "*"));
        assertThat(response, hasJsonBody(expected));
    }

    @Test
    public void fetchABookUsingXml() throws Exception {

        // given
        when(bookRepository.getBook(1000)).thenReturn(
                new Book().id(1000).author("Ennis").title("Hellblazer")
        );

        // when
        ClientResponse response = resource()
                .path("books/1000")
                .accept(MediaType.APPLICATION_XML)
                .get(ClientResponse.class);

        // then
        String expected = "" +
                "{\"id\":\"1000\"," +
                "    \"author\":\"Ennis\"," +
                "    \"title\":\"Hellblazer\"," +
                "    \"uri\":\"http://localhost:9998/books/1000\"}";

        assertThat(response, hasStatus(200));
        assertThat(response, hasHeader("Access-Control-Allow-Origin", "*"));
        //assertThat(response, hasXmlBody(expected));
    }

    @Test
    public void updateABook() throws Exception {

        // given
        when(bookRepository.saveOrUpdate(any(Book.class)))
                .thenAnswer(new ReturnsArgumentAt(0));

        String entity = "" +
                "{\"id\":\"1000\"," +
                "    \"author\":\"Ennis\"," +
                "    \"title\":\"Hellblazer\"," +
                "    \"uri\":\"http://localhost:9998/books/1000\"}";


        // when
        ClientResponse response = resource()
                .path("books/1000")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .put(ClientResponse.class, entity);

        // then
        assertThat(response, hasStatus(200));
        assertThat(response, hasHeader("Access-Control-Allow-Origin", "*"));
        assertThat(response, hasJsonBody(entity));
    }

    @Test
    public void createABook() throws Exception {

        // given
        when(bookRepository.saveOrUpdate(any(Book.class)))
                .thenAnswer(new Answer<Book>() {
                    @Override
                    public Book answer(InvocationOnMock invocationOnMock) throws Throwable {
                        return ((Book) invocationOnMock.getArguments()[0]).id(1017);
                    }
                });

        String entity = "" +
                "{\"author\":\"Ennis\"," +
                "    \"title\":\"Hellblazer\"}";


        // when
        ClientResponse response = resource()
                .path("books")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, entity);

        // then
        assertThat(response, hasStatus(201));
        assertThat(response, hasHeader("Access-Control-Allow-Origin", "*"));
        assertThat(response, hasHeader("Location", "http://localhost:9998/books/1017"));
    }

}
