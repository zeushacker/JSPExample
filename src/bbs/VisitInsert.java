package bbs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*;

//@WebServlet("/VisitInsert")
public class VisitInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		// 클라이언트가 write.html로 요청으로 전송한 값을 읽어옴
		String writer=request.getParameter("writer");
		String memo=request.getParameter("memo");
		
		System.out.println("작성자 :"+writer);
		System.out.println("내   용 :"+memo);
		
		// 가져온 파라미터 값을 데이터베이스에 저장
		StringBuffer sql = new StringBuffer();
		sql.append("insert into visit(no, writer, memo, regdate) ");
		sql.append("values(visit_seq.nextval, ?,?,sysdate)");
		
		// 데이터 베이스 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
/*			
			// 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 드라이버 매니저 (url, id, pw)
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl",
			        "scott", "tiger");
	*/		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//드라이버 매니저(url, id, pw)
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl"
					,"scott",
					"tiger");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, writer);
			pstmt.setString(2, memo);
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}finally {
			try {if(pstmt != null) pstmt.close();}catch(SQLException ss) {}
			try {if(con != null) con.close();}catch(SQLException ss) {}
		}
		
		
		// 강제 페이지 이동 sendRedirect(이동할 페이지)
		response.sendRedirect("VisitList");
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
