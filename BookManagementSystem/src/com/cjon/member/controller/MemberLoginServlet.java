package com.cjon.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.cjon.member.service.MemberService;


@WebServlet("/memberLogin")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public MemberLoginServlet() {
		super();

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		// 1. 입력받고
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String callback = request.getParameter("callback");
		// 2. 로직처리
		MemberService service = new MemberService();
		boolean result = service.loginMember(id, password);

		if (result) {
			session.setAttribute("ID", id);
		}
		
		int i = 0;
		
		System.out.println("========================================");
		System.out.println("No  SessionID  UserID");
		System.out.println("========================================");
		
		String user_id = (String) session.getAttribute("ID");
		System.out.print("(" + i + ")" + id);
		System.out.println("  userID = [" + user_id + "]");
		
		// 3. 출력처리
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");

		out.flush();
		out.close();
	}

}
