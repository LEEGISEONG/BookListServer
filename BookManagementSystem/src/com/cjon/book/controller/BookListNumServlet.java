package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.book.service.BookService;

@WebServlet("/bookListNum")
public class BookListNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BookListNumServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 입력받고
		String keyword = request.getParameter("keyword"); // 책에 대한 keyword를 받는부분
		String callback = request.getParameter("callback"); // JSONP처리를 위해서 사용
		// 2. 로직처리	
		BookService service = new BookService();		
		String result = service.getListNum(keyword);
		// 3. 출력처리
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");
		out.flush();
		out.close();
	}

}
