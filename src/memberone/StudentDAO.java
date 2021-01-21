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
			System.out.println("Connection ���� ����~~~~");
		}
		return conn;
	
	}
	
	// ������ �޼ҵ� �߰��ϴ� �̺κ� �ؿ��ٰ� �߰��Ͻÿ�.

	// ���̵� �Ű������� ���޹޾Ƽ� �´��� �ȸ´����� �˻�
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
	
	
	// �����ȣ�� �����ͺ��̽����� �˻��� ����� vector�� �����Ͽ� ������ �ִ� 
	// �޼ҵ� ����
	
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
	
	// ������ �����ͺ��̽��� ȸ�������͸� �߰��ϱ� ���Ͽ� �޼ҵ带 �߰�
	
	public boolean memberInsert(StudentVO vo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		boolean flag = false; //ȸ���߰� ���� ����
		
	  try {
		  conn = getConnection();
		  
		  String strQuery = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
		  
		  pstmt = conn.prepareStatement(strQuery);
		  
		  pstmt.setString(1, vo.getId());
		  pstmt.setString(2, vo.getPass());
		  pstmt.setString(3, vo.getName());
		  pstmt.setString(4, vo.getPhone1());
		  pstmt.setString(5, vo.getPhone2());
		  pstmt.setString(6, vo.getPhone3());
		  pstmt.setString(7, vo.getEmail());
		  pstmt.setString(8,vo.getZipcode());
		  pstmt.setString(9, vo.getAddress1());
		  pstmt.setString(10, vo.getAddress2());
		  
		  int count = pstmt.executeUpdate();
		  // ���̰� �߰��Ȱ��
		  if( count > 0) flag = true;
		  
	  }catch(Exception e) {
		 // e.printStackTrace();
		  System.out.println("Exception :"+e);
	  }finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
			if(conn != null) try {conn.close();}catch(SQLException s) {}
	  }
		
	  return flag;

	}// end memberInsert
	
	// loginCheck �޼ҵ� �߰� : �α��μ��� :1, ��й�ȣ ���� : 0, ���̵� ���� : -1
	public int loginCheck(String id, String pass) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int check = -1;// ���̵� ����
		
		try {
			conn = getConnection();
			String strQuery ="select pass from student where id=?";
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbPass = rs.getString("pass");
				if(pass.equals(dbPass)) check = 1;
				else check = 0;
			}
		}catch(SQLException ss) {
			ss.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
			if(conn != null) try {conn.close();}catch(SQLException s) {}
		}
		return check;
	}// end loginCheck
	
	
	// ���̵� ������ ȸ�������� ������ �޼ҵ� ����
	
	  public StudentVO getMember(String id) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			StudentVO vo = null;
			
			try {
		conn = getConnection();
		pstmt = conn.prepareStatement("select * from student where id=?");
		
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {// �ش� ���̵� ȸ���� �����ϸ�
			
			vo = new StudentVO();
			
			vo.setId(rs.getString("id"));
			vo.setPass(rs.getString("pass"));
			vo.setName(rs.getString("name"));
			vo.setPhone1(rs.getString("phone1"));
			vo.setPhone2(rs.getString("phone2"));
			vo.setPhone3(rs.getString("phone3"));
			vo.setEmail(rs.getString("email"));
			vo.setZipcode(rs.getString("zipcode"));
			vo.setAddress1(rs.getString("address1"));
			vo.setAddress2(rs.getString("address2"));
	     	}
		}catch (Exception se) {
			se.printStackTrace();
		} finally {	
			if (rs != null) try { rs.close(); } catch (SQLException se) {	}
			if (pstmt != null) 	try { pstmt.close(); } catch (SQLException se) {	}
			if (conn != null) 	try { conn.close(); 	} catch (SQLException se) { 	}
		  }
		return vo;
	}// end getMember
		
	  
	// ȸ�������� �����ϱ� ���� ���������� ó������ �޼ҵ� ����
	public void updateMember(StudentVO vo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(
		"update student set pass=?, phone1=?, phone2=?, "
		+ "phone3=?, email=?, zipcode=?, address1=?, "
		+ "address2=? where id=?");
			
			  pstmt.setString(1, vo.getPass());
			  pstmt.setString(2, vo.getPhone1());
			  pstmt.setString(3, vo.getPhone2());
			  pstmt.setString(4, vo.getPhone3());
			  pstmt.setString(5, vo.getEmail());
			  pstmt.setString(6,vo.getZipcode());
			  pstmt.setString(7, vo.getAddress1());
			  pstmt.setString(8, vo.getAddress2());
			  pstmt.setString(9, vo.getId());
						
		      pstmt.executeUpdate();
		
		}catch(Exception e) {
			 // e.printStackTrace();
			  System.out.println("Exception :"+e);
		  }finally {
				if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
				if(conn != null) try {conn.close();}catch(SQLException s) {}
		  }
	}// end updateMember
	  
	  
	  
	  
	  
	
	
	
	
	
}
