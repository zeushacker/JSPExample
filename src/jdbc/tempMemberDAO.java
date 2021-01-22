package jdbc;

import java.sql.*;
import java.util.*;
import jdbc.tempMemberVO;

public class tempMemberDAO {

	private final String JDBC_DRIVER="oracle.jdbc.driver.OracleDriver";
	private final String JDBC_URL="jdbc:oracle:thin:@localhost:1521:orcl";
	private final String USER="scott";
	private final String PASS ="tiger";
	
	public tempMemberDAO() {
	
		try {
			Class.forName(JDBC_DRIVER);
		}catch(Exception e) {
			System.out.println("Error :Jdbc 드라이버 로딩 실패 !");
		}
	}
	
	public Vector<tempMemberVO> getMemberList() {
		
		Vector<tempMemberVO> vecList = new Vector<tempMemberVO>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn= DriverManager.getConnection(JDBC_URL, USER, PASS);
			
			
			String strQuery ="select * from tempmember";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strQuery);
			
			while(rs.next()) {
				
				// vo 객체 생성
				tempMemberVO vo = new tempMemberVO();
				// 객체 데이터베이스에 저장된 값을 가져다 저장
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setMem_num1(rs.getString("mem_num1"));
				vo.setMem_num2(rs.getString("mem_num2"));
				vo.setEmail(rs.getString("e_mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress(rs.getString("address"));
				vo.setJob(rs.getString("job"));
				
				// 벡터에 추가
				vecList.add(vo);
	
			}
		}catch(Exception ee) {
			System.out.println("Exception :"+ee);
		}finally {
			
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(stmt != null) try {stmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		return vecList;
	}
}
