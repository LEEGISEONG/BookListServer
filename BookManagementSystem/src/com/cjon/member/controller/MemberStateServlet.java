package com.cjon.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

@WebServlet("/memberState")
public class MemberStateServlet extends HttpServlet {

	public MemberStateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("memberState");
		String callback = request.getParameter("callback");
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("ID");
		
		JSONObject obj = new JSONObject();
		obj.put("ID", id);
		
		String result=	obj.toString();
		out.println(callback + "(" + result + ")");

		out.flush();
		out.close();
		
		System.out.println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
