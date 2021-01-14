package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="InitParamAnnoServlet", 
                     urlPatterns = {"/InitParamAnno"},
                     initParams = {
                    		 @WebInitParam(name="dirPath", value="c:\\pk"),
                             @WebInitParam(name="userid", value="admin")})
public class InitParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		// 초기 파라미터 얻기
		String dirPath = getInitParameter("dirPath");
		/*  getInitParameter : name에 해당하는 초기화 파라미터 값을 리턴함
		 *                              name에 해당하는 파라미터 값이없으면 null 을 리턴함
		 */
		String userid= getInitParameter("userid");
		
		System.out.println("디렉토리 경로:"+dirPath);
		System.out.println("아이디 :"+userid);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>");
		out.print("디렉토리 경로:"+dirPath+"<br>");
		out.print("아이디 값:"+userid+"<br>");
		out.print("</body></html>");
		
		
	}

}
