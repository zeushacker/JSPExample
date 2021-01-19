package login;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class LogonDBBean {

	private static LogonDBBean instance = new LogonDBBean();
	
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	public LogonDBBean() {	}
	
	private Connection getConnection() throws Exception {
		
		Context initContext = new InitialContext();
	   
	     DataSource ds =(DataSource)initContext.lookup(
	    		 "java:/comp/env/jdbc/myoracle");
	     
	     return ds.getConnection();

	}
	
	// 회원인증 성공 : 1, 비밀번호 틀림 : 0, 아이디 없음 : -1
	public int userCheck(String id, String passwd) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		
		int x = -1;// 아이디가 존재하지 않음
		
		 try {
			 // 디비 연결
			 conn = getConnection();
			 
			 pstmt = conn.prepareStatement(
					 "select passwd from tempmember where id=?");
			 
			 pstmt.setString(1, id);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {// 아이디가 존재하면
				 
				 dbpasswd = rs.getString("passwd");
				 // 유저가 입력 비밀번호와 데이터베이스에 저장된 비밀번호가 같은지
				 if(dbpasswd.equals(passwd)) // 같으면
					 x = 1;// 인증 성공인 경우
				 else 
					 x = 0;// 비밀번호가 틀린경우
		
			 } else {
				 // 아이디가 없을 경우
					 x = -1;
			}
		 
		 } catch(Exception ee) {
			 ee.printStackTrace();
		 }finally {
			 
			 if(rs != null) try {rs.close();}catch(SQLException se) {}			 
			 if(pstmt != null) try {pstmt.close();}catch(SQLException se) {}
			 if(conn != null) try {conn.close();}catch(SQLException se) {}
		
		 }
	    return x;	
	}
}
