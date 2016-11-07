package com.cjon.rent.service;

import com.cjon.book.dao.BookDAO;
import com.cjon.rent.dao.RentDAO;

public class RentService {

	// 리스트를 가져오는 일을 하는 method
	public String getList(String keyword) {
	
		RentDAO dao = new RentDAO();
		String result = dao.select(keyword);

		return result;
	}

	public boolean rentBook(String id, String isbn) {
		RentDAO dao = new RentDAO();
		boolean result = dao.rent(id, isbn);
		return result;
	}

	public boolean returnBook(String id, String isbn) {
		RentDAO dao = new RentDAO();
		boolean result = dao.returnBook(id, isbn);
		return result;
	}

	public String getUsertBookList(String keyword) {
		RentDAO dao = new RentDAO();
		String result = dao.userBookSelect(keyword);
		return result;
	}

}