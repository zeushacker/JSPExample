package bbs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


//@WebServlet("/VisitList")
public class VisitList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		
		try {
			out.println("<html>");
			out.println("<head><title>방명록 리스트</title></head>");
			out.println("<body>");
			
			//쿼리 작성
			StringBuffer sql = new StringBuffer();
			sql.append("select no, writer, memo, regdate ");
			sql.append("from visit ");
			sql.append("order by no desc ");
			
			// 연결객체생성
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// 디비연결
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// 드라이버 매니저 (url, id, pw)
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:orcl",
				        "scott", "tiger");
							
				pstmt = con.prepareStatement(sql.toString());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
				
					int no = rs.getInt("no");
					String writer = rs.getString("writer");
					String memo = rs.getString("memo");
					java.sql.Date regdate = rs.getDate("regdate");
					
					out.println("<table align=center width=500 border=1>");
					
					out.println("<tr>");
					out.println("<th width=50>번호</th>");
					out.println("<td width=50 align=center>"+no+"</td>");
				
					out.println("<th width=70>작성자</th>");
					out.println("<td width=180 align=center>"+writer+"</td>");
				
					out.println("<th width=50>날짜</th>");
					out.println("<td width=100 align=center>"+regdate+"</td>");
					
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<th width=50>내용</th>");
					out.println("<td colspan=5>&nbsp;<textarea rows=3 cols=50>"
					+memo+"</textarea></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<p>");
				}
				
			}catch(SQLException ss ) {
				ss.printStackTrace();
			}catch(ClassNotFoundException fe) {
				fe.printStackTrace();
			}finally {
				try {if(rs != null) rs.close();}catch(SQLException s) {}
				try {if(pstmt != null) pstmt.close();}catch(SQLException s) {}
				try {if(con != null) con.close();}catch(SQLException s) {}
			}
			
			out.println("<p align=center><a href=/JSPExample/bbs/write.html>글쓰기</a></p>");
			out.println("</body>");
			out.println("</html>");
			
			
		}finally {
			out.close();
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
