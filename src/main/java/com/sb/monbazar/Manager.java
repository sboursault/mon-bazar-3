package com.sb.monbazar;


import com.sb.monbazar.repositories.BookRepository;

public class Manager {

    private static BookRepository bookRepository = new BookRepository();

    public static BookRepository getBookRepository() {
        return bookRepository;
    }

    public static void setBookRepository(BookRepository bookRepository) {
        Manager.bookRepository = bookRepository;
    }
}
