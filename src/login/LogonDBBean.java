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
	
	// ȸ������ ���� : 1, ��й�ȣ Ʋ�� : 0, ���̵� ���� : -1
	public int userCheck(String id, String passwd) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		
		int x = -1;// ���̵� �������� ����
		
		 try {
			 // ��� ����
			 conn = getConnection();
			 
			 pstmt = conn.prepareStatement(
					 "select passwd from tempmember where id=?");
			 
			 pstmt.setString(1, id);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {// ���̵� �����ϸ�
				 
				 dbpasswd = rs.getString("passwd");
				 // ������ �Է� ��й�ȣ�� �����ͺ��̽��� ����� ��й�ȣ�� ������
				 if(dbpasswd.equals(passwd)) // ������
					 x = 1;// ���� ������ ���
				 else 
					 x = 0;// ��й�ȣ�� Ʋ�����
		
			 } else {
				 // ���̵� ���� ���
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
