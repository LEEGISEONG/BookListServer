package com.cjon.comment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.comment.service.CommentService;


@WebServlet("/deleteCommentBook")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentDeleteServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 입력받고
				String isbn = request.getParameter("isbn").trim();
				String id = request.getParameter("id").trim();
				
				String callback = request.getParameter("callback");
				
				// 2. 로직처리
				CommentService service = new CommentService();
				boolean result = service.deleteCommentBook(isbn, id);
				// 3. 출력처리
				response.setContentType("text/plain; charset=utf8");
				PrintWriter out = response.getWriter();
				out.println(callback + "(" + result + ")");
				out.flush();
				out.close();		}

}
