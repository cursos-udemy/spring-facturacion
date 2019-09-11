package com.jendrix.udemy.facturacion.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPages;
	private int itemsByPage;
	private int currentPage;
	private List<PageItem> pages;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.pages = new ArrayList<PageItem>();

		itemsByPage = 6;
		totalPages = page.getTotalPages();
		currentPage = page.getNumber() + 1;

		int since, until;
		if (totalPages <= itemsByPage) {
			since = 1;
			until = totalPages;
		} else {
			if (currentPage <= itemsByPage / 2) {
				since = 1;
				until = itemsByPage;
			} else if (currentPage >= totalPages - itemsByPage / 2) {
				since = totalPages - itemsByPage + 1;
				until = itemsByPage;
			} else {
				since = currentPage - itemsByPage / 2;
				until = itemsByPage;
			}
		}

		for (int i = 0; i < until; i++) {
			pages.add(new PageItem(since + i, currentPage == since + i));
		}

	}

	public String getUrl() {
		return url;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<PageItem> getPages() {
		return pages;
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean isHasNext() {
		return page.hasNext();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
