package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Sport")
public class SportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// post 방식에서의 인코딩 처리
		request.setCharacterEncoding("utf-8");
	    // post 방식으로 요청한 경우에는 파라미터값을 얻기 전에 서블릿의 doGet 또는
		// doPost 메소드내에서 request.setCharacterEncoding("utf-8")메소드를 
		// 사용해서 한글 인코딩을 처리해줌
		
	     /* Get 방식으로 처리할때
	      *  - get 방식으로 처리하나 경우 톰켓의 설정 파일인 server.xml 파일에서
	      *    설정함    URIEncoding="UTF-8"로 처리함
	      */
		
		// checkbox에서 선택한 값을 배열로 리턴받음
		String[]  sports = request.getParameterValues("sports");
		String gender = request.getParameter("gender");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html>");
		out.print("<head><title>좋아하는 운동및 성별</title></head>");
		out.print("<body>");
		
		for(String sport : sports) 
		{
			out.print("좋아하는 운동 :"+sport+"<br>");
		}
		
		out.print("성별 :"+gender+"<br>");
		out.print("</body>");
		out.print("</html>");
		
		
	}

}
