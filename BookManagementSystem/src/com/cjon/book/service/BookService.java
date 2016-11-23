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

	public boolean deleteBook(String isbn) {

		BookDAO dao = new BookDAO();
		boolean result = dao.delete(isbn);

		return result;
	}

}
