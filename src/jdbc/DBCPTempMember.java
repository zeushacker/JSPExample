package jdbc;


	import java.sql.*;
	import java.util.*;
	import jdbc.tempMemberVO;

    import javax.sql.DataSource;
	import javax.naming.InitialContext;
    import javax.naming.NamingException;
    import javax.naming.Context;
	
	
	public class DBCPTempMember {

		DataSource ds;
		
		public DBCPTempMember() {
		try {
			 Context initContext = new InitialContext();
		     DataSource ds =(DataSource)initContext.lookup(
		    		 "java:/comp/env/jdbc/myoracle");
		}catch(NamingException ne) {
			ne.printStackTrace();
		   }
			
		}
		
		public Vector<tempMemberVO> getMemberList() {
			
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
				
			
			
			Vector<tempMemberVO> vecList = new Vector<tempMemberVO>();
			
			try {
			
			    conn = ds.getConnection();
				
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
	
	

