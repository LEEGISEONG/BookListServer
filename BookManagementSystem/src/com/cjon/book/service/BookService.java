package com.cjon.book.service;

import com.cjon.book.dao.BookDAO;

public class BookService {

	// 리스트 출력 메소드
	public String getList(String keyword, String start) {

		BookDAO dao = new BookDAO();
		String result = dao.select(keyword, start);

		return result;
	}

	// 삭제처리 메소드
	public boolean deleteBook(String isbn) {

		BookDAO dao = new BookDAO();
		boolean result = dao.delete(isbn);

		return result;
	}

}
