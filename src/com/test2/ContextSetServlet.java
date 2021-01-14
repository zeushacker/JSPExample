package com.test2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ContextSet")
public class ContextSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//속성 설정
		String name="홍길동";
		int age =20;
		
		// setAttribute(name, value) 메소드를 사용하여 저장함
		getServletContext().setAttribute("name", name);
		getServletContext().setAttribute("age", age);
		
		// 이렇게 저장된 속성 값은 어플리케이션이 종료되기 전까지 계속 사용함
	
	
	}

}
