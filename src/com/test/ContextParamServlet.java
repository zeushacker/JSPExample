package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ContextParam")
public class ContextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	// 컨텍스트 파라미터 값 얻기
		String driver = getServletContext().getInitParameter("driver");
		String savePath =getServletContext().getInitParameter("savePath");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>");
		out.print("드라이브 명 :"+driver+"<br>");
		out.print("저장 경로 :"+savePath+"<br>");		
	   out.print("</body></html>");
	
	}

}
