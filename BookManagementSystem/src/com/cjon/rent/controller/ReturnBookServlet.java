package com.cjon.rent.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.rent.service.RentService;

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ReturnBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 입력받고
		String id = request.getParameter("id"); // 책에 대한 keyword를 받는부분
		String isbn = request.getParameter("isbn"); // 책에 대한 keyword를 받는부분
		String callback = request.getParameter("callback"); // JSONP처리를 위해서 사용
		// 2. 로직처리하고(DB처리포함)
		RentService service = new RentService();		
		boolean result = service.returnBook(id, isbn);		
		// 3. 출력처리
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");
		out.flush();
		out.close();	}

}
