package com.sb.monbazar.resources.representations;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement // allows jersey to marshall this to xml
public class BookList {
    ArrayList<BookSummary> books = new ArrayList<>();

    public BookList add(BookSummary book) {
        books.add(book);
        return this;
    }

    public BookList addAll(Collection<BookSummary> list) {
        books.addAll(list);
        return this;
    }

    public ArrayList<BookSummary> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookSummary> books) {
        this.books = books;
    }

    public static class BookSummary {

        private Long id;
        private String title = "";
        private String author = "";
        private String uri = "";

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        @Override
        public String toString() {
            return "BookSummary [id=" + id + ", title=" + title + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            BookSummary other = (BookSummary) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }

    }
}
