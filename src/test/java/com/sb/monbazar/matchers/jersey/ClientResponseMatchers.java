package com.sb.monbazar.matchers.jersey;

import com.google.common.base.Charsets;
import com.google.common.base.Converter;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.sun.jersey.api.client.ClientResponse;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.hamcrest.*;
import org.hamcrest.core.IsEqual;
import org.mockito.cglib.core.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * Created by me on 16/01/16.
 */
public class ClientResponseMatchers {

    public static Matcher<ClientResponse> hasJsonBody(final String expectedValue) {
        return new BaseMatcher<ClientResponse>() {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualJsonBody, expectedJsonBody;

            @Override
            public boolean matches(Object actualValue) {
                try {
                    ClientResponse actualResponse = (ClientResponse) actualValue;
                    expectedJsonBody = mapper.readTree(expectedValue);
                    actualJsonBody = mapper.readTree(actualResponse.getEntityInputStream());
                    return actualJsonBody.equals(expectedJsonBody);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendValue(expectedJsonBody);
            }

            @Override
            public void describeMismatch(Object actualValue, Description description) {
                description.appendText("was ").appendValue(actualJsonBody);
            }
        };
    }

    public static Matcher<ClientResponse> hasStatus(final int expectedValue) {
        return new BaseMatcher<ClientResponse>() {
            @Override
            public boolean matches(Object actualValue) {
                return ((ClientResponse) actualValue).getStatus() == expectedValue;
            }

            @Override
            public void describeTo(Description description) {
                description.appendValue(expectedValue);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                description.appendText("was ").appendValue(((ClientResponse) item).getStatus());
            }
        };
    }

    public static Matcher<ClientResponse> hasHeader(final String key, final String value) {
        final Matcher<Iterable<? extends String>> containsMatcher = Matchers.contains(value);
        return new BaseMatcher<ClientResponse>() {
            @Override
            public boolean matches(Object actualResponse) {
                return containsMatcher.matches(getHeaderValues(actualResponse, key));
            }

            @Override
            public void describeTo(Description description) {
                description.appendValue(String.format("%s:%s", key, value));
            }

            @Override
            public void describeMismatch(Object actualResponse, Description description) {
                List<String> values = getHeaderValues(actualResponse, key);
                if (values == null) {
                    description.appendText("no header found with key ").appendValue(key);
                } else {
                    values = Lists.transform(values, new Function<String, String>() {
                        @Override
                        public String apply(String value) {
                            return String.format("%s:%s", key, value);
                        }
                    });
                    description.appendText("was(were) ").appendValueList("", ",", "", values);
                }


            }

            private List<String> getHeaderValues(Object actualResponse, String key) {
                return ((ClientResponse) actualResponse).getHeaders().get(key);
            }
        };
    }
}
