package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.book.service.BookService;

@WebServlet("/bookInsert")
public class BookInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BookInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 입력받고
		String img = request.getParameter("img");
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String translator = request.getParameter("translator");
		String supplement = request.getParameter("supplement");
		String publisher = request.getParameter("publisher");
		String price = request.getParameter("price");
		String date = request.getParameter("date");
		String page = request.getParameter("page");		

		String callback = request.getParameter("callback");
		// 2. 로직처리
		BookService service = new BookService();
		boolean result = service.insertBook(img, isbn, title, author, translator, supplement, publisher, price, date, page);
		// 3. 출력처리
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");
		out.flush();
		out.close();
	}
}
