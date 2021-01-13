package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContextFile")
public class ContextFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//파일 읽기
		String readFile = "/WEB-INF/testFile.txt";
    // 입력 객체 생성하여 파일에 한 라인씩 읽어 가져옴
		InputStream is = getServletContext().getResourceAsStream(readFile);
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(is));
		
		// 브라우저로 출력
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String str =reader.readLine();
		out.print("<html><body>");
		while(str != null) {
			
			out.print(str+"<br>");
			str = reader.readLine();
		}
		reader.close();
		out.print("</body></html>");
	
	}

}
