package com.cjon.comment.service;

import com.cjon.comment.dao.CommentDAO;

public class CommentService {

	public String getCommentsBook(String isbn) {
		CommentDAO dao = new CommentDAO();
		String result = dao.getComments(isbn);

		return result;
	}

	public boolean insertCommentBook(String isbn, String title, String author, String date, String text) {
		CommentDAO dao = new CommentDAO();
		boolean result = dao.insertComment(isbn, title, author, date, text);

		return result;
	}

	public boolean deleteCommentBook(String isbn, String id) {
		CommentDAO dao = new CommentDAO();
		boolean result = dao.deleteComment(isbn, id);

		return result;
	}

	public String getCommentsList(String keyword) {
		CommentDAO dao = new CommentDAO();
		String result = dao.getCommentsList(keyword);

		return result;
	}

}
