package com.test;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostPreServlet
 */
@WebServlet("/PostPre")
public class PostPreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("init");
	}

	public void destroy() {
		System.out.println("destroy");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    System.out.println("doGet");
	}
	
	// 선처리(init 메소드가 호출되기 전 처리)
	// 선처리작업 지정 어노테이션
	@PostConstruct
	public void initMethod() {
		System.out.println("initMethod");
	}
	
	// 후처리 (destroy 메소드가 호출된 후 실행)
	// 후처리 지정 어노테이션
	@PreDestroy
	public void clean() {
		System.out.println("clean");
	}
	
}
