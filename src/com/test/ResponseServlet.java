package com.test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


@WebServlet("/Response")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			//MIME 타입설정 (Multipurpose Internet Mail Extensions)
			// MIME : 파일변환 
			// - 파일을 텍스트문자로 전환하여 사용함, 웹을 통해서 여러 형태의 파일을 전달할때 사용
			response.setContentType("text/html;charset=utf-8");
			
			// PrintWriter 객체 생성
			PrintWriter out = response.getWriter(); 
			// 문자 데이터를 출력
			
			// 문자열을 이용하여 html 작성 및 출력
			out.print("<html>");
			out.print("<body>");
			
			out.print("ResponseServelt 요청 성공!!!");
		
			out.print("</body>");
			out.print("</html>");
		
		
		}

}
