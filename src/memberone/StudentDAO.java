package memberone;

import java.sql.*;
import java.util.Vector;

import javax.sql.*;
import javax.naming.*;

public class StudentDAO {

	private Connection getConnection() {
		
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
		     DataSource ds =(DataSource)initContext.lookup(
		    		 "java:/comp/env/jdbc/myoracle");
			
			conn = ds.getConnection();
			
		}catch(Exception e) {
			System.out.println("Connection 생성 실패~~~~");
		}
		return conn;
	
	}
	
	// 앞으로 메소드 추가하는 이부분 밑에다가 추가하시오.

	// 아이디를 매개변수로 전달받아서 맞는지 안맞는지를 검사
	public boolean idCheck(String id) {
		
		boolean result = true;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(
					"select * from student where id=?");
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) result = false;
	
		}catch(SQLException ss) {
			ss.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
			if(conn != null) try {conn.close();}catch(SQLException s) {}
		}
		return result;
		
	}// end idCheck
	
	
	// 우편번호를 데이터베이스에서 검색한 결과를 vector에 저장하여 리턴해 주는 
	// 메소드 구현
	
	public Vector<ZipCodeVO> zipcodeRead(String dong) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();
		
		try {
			
			conn =getConnection();
			
			String strQuery =
					"select * from zipcode where dong like '"+dong+"%'";
			
			pstmt = conn.prepareStatement(strQuery);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ZipCodeVO tempZipcode = new ZipCodeVO();
				tempZipcode.setZipcode(rs.getString("zipcode"));
				tempZipcode.setSido(rs.getString("sido"));
				tempZipcode.setGugun(rs.getString("gugun"));
				tempZipcode.setDong(rs.getString("dong"));
				tempZipcode.setRi(rs.getString("ri"));
				tempZipcode.setBunji(rs.getString("bunji"));
				vecList.addElement(tempZipcode);
			}
			
		}catch(SQLException ss) {
			ss.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
			if(conn != null) try {conn.close();}catch(SQLException s) {}
		}
		
		return vecList;
	}// end zipCode
	
	
	
	
	
	
	
	
	
	
}
