package com.sb.monbazar.resources;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.sb.monbazar.Manager;
import com.sb.monbazar.core.model.Book;
import com.sb.monbazar.repositories.BookRepository;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class BooksResourceTest extends JerseyTest {

    public BooksResourceTest() {
        super("com.sb.monbazar.resources");
    }

    @Mock
    BookRepository bookRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Manager.setBookRepository(bookRepository);
    }

    /*When testing a REST resource, there are usually a few orthogonal responsibilities the tests should focus on:

        the HTTP response code
        other HTTP headers in the response
        the payload (JSON, XML)*/

    // RestAssured.given()

    @Test
    public void fetchesSeveralBooks() throws Exception {

        // given
        when(bookRepository.search()).thenReturn(Lists.newArrayList(
                new Book().id(1000).author("Ennis").title("Hellblazer"),
                new Book().id(1005).author("Pratt").title("Tango")
        ));

        // when
        String responseMsg = resource().path("books")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);

        // then
        String json = "" +
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

        Assert.assertThat(responseMsg, equalToJson(json));
    }

    @Test
    public void fetchesABookfromItsId() throws Exception {

        // given
        when(bookRepository.getBook(1000)).thenReturn(
                new Book().id(1000).author("Ennis").title("Hellblazer")
        );

        // when
        String responseMsg = resource()//.path("books/1000")
                .uri(URI.create("books/100O"))
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);

        // then
        String json = "" +
                "{\"id\":\"1000\"," +
                "    \"author\":\"Ennis\"," +
                "    \"title\":\"Hellblazer\"," +
                "    \"uri\":\"http://localhost:9998/books/1000\"}";

        Assert.assertThat(responseMsg, equalToJson(json));
    }

    private Matcher<String> equalToJson(final String expectedValue) {
        return new TypeSafeMatcher<String>() {

            ObjectMapper mapper = new ObjectMapper();

            @Override
            protected boolean matchesSafely(String actualValue) {
                try {
                    return mapper.readTree(actualValue).equals(mapper.readTree(expectedValue));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendValue(expectedValue);
            }
        };
    }

}
