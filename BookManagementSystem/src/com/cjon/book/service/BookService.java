package com.cjon.book.service;

import com.cjon.book.dao.BookDAO;

public class BookService {

	// 리스트를 가져오는 일을 하는 method
	public String getList(String keyword, String start) {
		// 일반적인 로직처리 
		// 추가적으로 DB처리
	
		BookDAO dao = new BookDAO();
		String result = dao.select(keyword, start);

		return result;
	}

	public boolean updateBook(String isbn, String title, String author, String price) {
		BookDAO dao = new BookDAO();
		boolean result = dao.update(isbn, title, author, price);
		return result;
	}

	public boolean deleteBook(String isbn) {

		BookDAO dao = new BookDAO();
		boolean result = dao.delete(isbn);

		return result;
	}

	public boolean insertBook(String img, String isbn, String title, String author, String translator,
			String supplement, String publisher, String price, String date, String page) {
		BookDAO dao = new BookDAO();
		boolean result = dao.insert(img, isbn, title, author, translator, supplement, publisher, price, date, page);

		return result;
	}

	public String detailsBook(String isbn) {
		BookDAO dao = new BookDAO();
		String result = dao.details(isbn);

		return result;
	}

	public String infoBook(String isbn) {
		BookDAO dao = new BookDAO();
		String result = dao.info(isbn);

		return result;
	}

	public String getListNum(String keyword) {
		
		BookDAO dao = new BookDAO();
		String result = dao.getListNum(keyword);

		return result;
	}

}
