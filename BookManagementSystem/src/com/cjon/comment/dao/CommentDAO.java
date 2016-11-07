package com.cjon.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.book.common.DBTemplate;
import com.sun.corba.se.spi.orbutil.fsm.Input;

public class CommentDAO {

	public String getComments(String isbn) {
		// Database처리
		// 1. Driver Loading 
		// 2. Connection 생성
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			 String sql = "select cid, ctitle, ctext, cauthor, cdate "
			 + "from book_comment where bisbn=?";
			System.out.println("get comments");
			System.out.println(isbn);

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			int i = 0 ; 
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getString("cid"));
				obj.put("title", rs.getString("ctitle"));
				obj.put("text", rs.getString("ctext"));
				obj.put("author", rs.getString("cauthor"));
				obj.put("date", rs.getString("cdate"));
				arr.add(obj);
			}
			result = arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}
		return result;
	}

	public boolean insertComment(String isbn, String title, String author, String date, String text) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			System.out.println("insertComment ");
			System.out.println(isbn);
			System.out.println(title);
			System.out.println(author);
			System.out.println(date);
			System.out.println(text);
			
			String sql = "insert into book_comment values(" + "cid" // cid
					+ ", ?" // bisbn
					+ ", ?" // ctitle
					+ ", ?" // cauthor
					+ ", ?" // cdate
					+ ", ?)"; // ctext

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.setString(2, title);
			pstmt.setString(3, author);
			pstmt.setString(4, date);
			pstmt.setString(5, text);

			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if (count == 1) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}
		return result;
	}

	public boolean deleteComment(String isbn, String id) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			System.out.println("Delete comment ");
			System.out.println(isbn);
			System.out.println(id);
			String sql = "delete from book_comment where cid=? and bisbn=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, isbn);

			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if (count == 1) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}
		return result;

	}

	public String getCommentsList(String keyword) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			System.out.println("comment list");
			System.out.println(keyword);
			String sql = "select bisbn, cid, ctitle, ctext, cauthor, cdate "
					+ "from book_comment " 
					+ "where ctext like ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			int i = 0 ; 
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("cid", rs.getString("cid"));
				obj.put("ctitle", rs.getString("ctitle"));
				obj.put("ctext", rs.getString("ctext"));
				obj.put("author", rs.getString("cauthor"));
				obj.put("date", rs.getString("cdate"));
				arr.add(obj);
			}
			result = arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}
		return result;
	
	}

}
