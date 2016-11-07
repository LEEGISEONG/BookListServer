package com.cjon.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.book.common.DBTemplate;
import com.sun.corba.se.spi.orbutil.fsm.Input;

public class MemberDAO {

	public boolean insert(String id, String password) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		try {
			System.out.println("insert member");
			System.out.println(id);
			System.out.println(password);
			
			String sql = "insert into member (id, password) values(?,?)";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
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

	public boolean loging(String id, String password) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		try {
			System.out.println("Login member");
			System.out.println(id);
			System.out.println(password);
			
			String sql = "select password from member where id=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				String pw=rs.getString("password").trim();
					
				if(pw.equals(password)){
					result=true;
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		
		if(result)
			System.out.println("login success");
		else
			System.out.println("login fails");
		return result;
	
	}

}
















