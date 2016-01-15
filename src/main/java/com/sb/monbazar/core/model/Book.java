package com.sb.monbazar.core.model;


public class Book {

    private Long id;
    private String title = "";
    private String author = "";

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

    public Book id(Long id) {
        this.setId(id);
        return this;
    }

    public Book id(Integer id) {
        return id(Long.valueOf(id));
    }

    public Book title(String title) {
        this.setTitle(title);
        return this;
    }

    public Book author(String author) {
        this.setAuthor(author);
        return this;
    }

}
