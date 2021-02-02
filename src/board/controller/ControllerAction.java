package board.controller;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;


public class ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 명령어와 명령어 처리 클래스를 쌍으로 저장(맵)
	private Map<String, Object> commandMap =
					new HashMap<String, Object>();
	
	/*  명령어와 처리클래스가 매핑되어 있는 properties 파일을 읽어서 
	 *  Map객체인 commandMap 에 저장
	 *  명령어와 처리클래스가 매핑되어 있는 properties 파일은
	 *  CommandPro.properties 파일임
	 */
	@SuppressWarnings("all")
	public void init(ServletConfig config) throws ServletException {
    // web.xml 에서   propertyConfig에 해당하는 init-param 값을 읽어옴
	String props = config.getInitParameter("propertyConfig");
	
	// 명령어와 처리클래스의 매핑정보를 저장할 Properties 객체를 생성함
	Properties pr = new Properties();
	
	// 파일을 읽어올 스트림 객체 생성
	FileInputStream f = null;
	// 파일 경로지정
	String path = config.getServletContext().getRealPath("/WEB-INF");
	
	try {
		
		// CommandPro.properties 파일의 내용을 읽어옴
		f = new FileInputStream(new File(path, props));
		
		// 읽어온 파일 정보를 Properties 객체에 저장
		pr.load(f);
	}catch(IOException e) {
		throw new ServletException(e);
	}finally {
		if(f != null) try {f.close();}catch(IOException ex) {}
	}
		
	Iterator<Object> keyIter = pr.keySet().iterator();
	// 객체를 하나씩 꺼내서 그 객체명으로 Properties 객체에 저장된 객체에 접근해서
	// 키값을 꺼내서 저장
	while(keyIter.hasNext()) {
		
		String command =(String)keyIter.next();
		String className = pr.getProperty(command);
		
		try {
			// 해당 문자열을 클래스로 만듬
			Class commandClass = Class.forName(className);
			//해당클래스의 객체를 생성
			Object commandInstance =  commandClass.newInstance();
			//map객체인 commandMap에 객체 저장
			commandMap.put(command, commandInstance);
		}catch(ClassNotFoundException e) {
			throw new ServletException(e);
		}catch(InstantiationException e) {
			throw new ServletException(e);
		}catch(IllegalAccessException e) {
			throw new ServletException(e);
		}
	}
}

	// 사용자의 요청을 분석해서 해당하는 작업을 처리하는 메소드
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String view = null;
		CommandAction com = null;
		
		try {
			
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath())==0) {
				command =
						command.substring(request.getContextPath().length());
			}
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
			
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

}
