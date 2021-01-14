package com.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Hello")

/*  맵핑값을 여러개 지정하는 형식
 *  @WebServlet(name="서블릿이름", urlPatterns={"/xxx.aaa","/yyy","/zzz"}
 */
//맵핑값을 여러개 지정하는 방법
//@WebServlet(name="MyServlet", urlPatterns = {"/xxx.do","/yyy","/zzz"})
@WebServlet(name="MyServlet", value = {"/xxx.do","/yyy.zz","/zzz.cc"})
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HelloServlet() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("HelloServlet 요청");
		
	}

}
