package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.book.service.BookService;

@WebServlet("/deleteBook")
public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookDeleteServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 입력받는 부분
		String isbn = request.getParameter("isbn");
		String callback = request.getParameter("callback");
		// 2. 로직처리 부분
		BookService service = new BookService();
		boolean result = service.deleteBook(isbn);
		// 3. 출력처리 부분
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");
		out.flush();
		out.close();
	}

}
