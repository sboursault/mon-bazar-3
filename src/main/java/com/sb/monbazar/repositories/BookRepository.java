package com.sb.monbazar.repositories;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.sb.monbazar.core.model.Book;
import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.utils.Preconditions;

import java.util.List;

public class BookRepository {

    public Book getBook(long id) {
        Item item = ObjectifyService.ofy().load().type(Item.class).id(id).now();
        return toBook().apply(item);
    }

    public List<Book> search() {
        List<Item> items = ObjectifyService.ofy().load().type(Item.class).limit(50).list();
        return Lists.transform(items, toBook());
    }

    public Book saveOrUpdate(Book entity) {
        Preconditions.checkNotNull(entity, "book must be set");
        Preconditions.checkNotBlank(entity.getTitle(), "book.title must be set");
        Item item = toItem().apply(entity);
        Key<Item> key = ObjectifyService.ofy().save().entity(item).now();
        return getBook(key.getId());
    }


    private Function<Item, Book> toBook() {
        return new Function<Item, Book>() {
            @Override
            public Book apply(Item source) {
                return new Book()
                        .id(source.getId())
                        .title(source.getTitle())
                        .author(source.getAuthor());
            }
        };
    }

    private Function<Book, Item> toItem() {
        return new Function<Book, Item>() {
            @Override
            public Item apply(Book source) {
                return new Item()
                        .id(source.getId())
                        .title(source.getTitle())
                        .author(source.getAuthor());
            }
        };
    }
}
