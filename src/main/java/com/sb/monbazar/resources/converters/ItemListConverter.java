package com.sb.monbazar.resources.converters;

import java.util.ArrayList;
import java.util.List;

import com.sb.monbazar.core.model.Item;
import com.sb.monbazar.resources.BooksResource;
import com.sb.monbazar.resources.representations.BookSummary;

public class ItemListConverter {

	List<Item> items;

	public ItemListConverter(List<Item> items) {
		super();
		this.items = items;
	}

	public static ItemListConverter from(List<Item> items) {
		return new ItemListConverter(items);
	}

	public List<BookSummary> toBookList() {

		List<BookSummary> targetList = new ArrayList<BookSummary>();
		for (Item item : items) {
			BookSummary target = new BookSummary();
			target.setId(item.getId());
			target.setTitle(item.getTitle());
			target.setAuthor(item.getAuthor());
			target.setUri(BooksResource.PATH + item.getId());
			targetList.add(target);
		}
		return targetList;

	}
}
 