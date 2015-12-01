package com.sb.monbazar.resources.converters;

import java.util.ArrayList;
import java.util.List;

import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.resources.BooksResource;
import com.sb.monbazar.resources.representations.BookList;

public class ItemListConverter {

	List<Item> items;

	public ItemListConverter(List<Item> items) {
		super();
		this.items = items;
	}

	public static ItemListConverter from(List<Item> items) {
		return new ItemListConverter(items);
	}

	public BookList toBookList() {
		BookList targetList = new BookList();
		for (Item item : items) {
			targetList.add(convertToBookSummary(item));
		}
		return targetList;
	}

	private BookList.BookSummary convertToBookSummary(Item item) {
		BookList.BookSummary target = new BookList.BookSummary();
		target.setId(item.getId());
		target.setTitle(item.getTitle());
		target.setAuthor(item.getAuthor());
		target.setUri(BooksResource.PATH + item.getId());
		return target;
	}
}
 