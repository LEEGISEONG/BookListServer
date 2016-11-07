package com.cjon.rent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.book.common.DBTemplate;

public class RentDAO {

	public String select(String keyword) {
		// Database처리가 나와요!
		// 1. Driver Loading 
		// 2. Connection 생성
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			
			String sql = "select bisbn, bimgbase64, btitle, bauthor, brent "
					   + "from book where btitle like ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("img", rs.getString("bimgbase64"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
				obj.put("rent", rs.getString("brent"));
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

	public boolean rent(String id, String isbn) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			System.out.println("rent ");
			System.out.println(id);
			System.out.println(isbn);
			
			String findsql = "select bisbn from member where id=?";
			
			pstmt= con.prepareStatement(findsql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			String get = rs.getString("bisbn");
			System.out.println("가지고 온거 "+get);
			if(get != null){
				
				System.out.println("또 빌리면 안되요");
				return false;
			}else{
				System.out.println(rs.getString("bisbn")+ "없네");	
			}
			
			String sql = "update book set brent=? where bisbn=?";
			
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, isbn);

			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			
			String sqlMember = "update member set bisbn=? where id=?";
			pstmt= con.prepareStatement(sqlMember);
			pstmt.setString(1, isbn);
			pstmt.setString(2, id);
			
			int countMember = pstmt.executeUpdate();
			
			if( count == 1 && countMember==1 ) {
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

	public boolean returnBook(String id, String isbn) {


		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		try {
			System.out.println("return  ");
			System.out.println(id);
			System.out.println(isbn);
			String sql = "update book set brent=? where bisbn=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setString(2, isbn);

			
			int count = pstmt.executeUpdate();
			
			
			String sqlMember = "update member set bisbn=? where id=?";
			pstmt= con.prepareStatement(sqlMember);
			pstmt.setString(1, null);
			pstmt.setString(2, id);
			
			int countMember = pstmt.executeUpdate();
			
			if( count == 1 && countMember==1 ) {
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

	public String userBookSelect(String keyword) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			
			String sql = "select bisbn, bimgurl, btitle, bauthor "
					   + "from book where brent=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("img", rs.getString("bimgurl"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
				arr.add(obj);
			}
			result = arr.toJSONString();
			System.out.println(keyword);
			System.out.println(result);
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