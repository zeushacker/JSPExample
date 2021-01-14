package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


@WebServlet("/Member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
/*
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
*/
	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		// post 방식의 인코딩 처리(한글)
		// 요청
		request.setCharacterEncoding("utf-8");
		Enumeration<String> enu = request.getParameterNames();
		//html 태그 의name값을 얻기 위해서 getParameterNames() 메소드를 사용해서
		//Enumeration 타입으로 리턴 받음
		
		// 응답
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html>");
		out.print("<body>");
		//hasMoreElements() 메소드와 nextElement() 를 사용하여 
		//name 값을 얻음
		//getParameter() 메소드를 사용하여 value 값을 얻어서 출력한다.
		while(enu.hasMoreElements()) {
			String name=enu.nextElement();
			String value=request.getParameter(name);
			out.print(name+":" +value+"<br>");
		}
		
		
		
		out.print("</body>");
		out.print("</html>");
	}

}
