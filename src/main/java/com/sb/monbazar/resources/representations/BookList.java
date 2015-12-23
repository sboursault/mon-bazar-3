package com.sb.monbazar.resources.representations;

import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement // allows jersey to marshall this to xml
public class BookList {
    ArrayList<Book> books = new ArrayList<>();

    public BookList add(Book book) {
        books.add(book);
        return this;
    }

    public BookList addAll(Collection<Book> list) {
        books.addAll(list);
        return this;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }


}
