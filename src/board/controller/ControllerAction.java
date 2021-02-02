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

	// ��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����(��)
	private Map<String, Object> commandMap =
					new HashMap<String, Object>();
	
	/*  ��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ �о 
	 *  Map��ü�� commandMap �� ����
	 *  ��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������
	 *  CommandPro.properties ������
	 */
	@SuppressWarnings("all")
	public void init(ServletConfig config) throws ServletException {
    // web.xml ����   propertyConfig�� �ش��ϴ� init-param ���� �о��
	String props = config.getInitParameter("propertyConfig");
	
	// ��ɾ�� ó��Ŭ������ ���������� ������ Properties ��ü�� ������
	Properties pr = new Properties();
	
	// ������ �о�� ��Ʈ�� ��ü ����
	FileInputStream f = null;
	// ���� �������
	String path = config.getServletContext().getRealPath("/WEB-INF");
	
	try {
		
		// CommandPro.properties ������ ������ �о��
		f = new FileInputStream(new File(path, props));
		
		// �о�� ���� ������ Properties ��ü�� ����
		pr.load(f);
	}catch(IOException e) {
		throw new ServletException(e);
	}finally {
		if(f != null) try {f.close();}catch(IOException ex) {}
	}
		
	Iterator<Object> keyIter = pr.keySet().iterator();
	// ��ü�� �ϳ��� ������ �� ��ü������ Properties ��ü�� ����� ��ü�� �����ؼ�
	// Ű���� ������ ����
	while(keyIter.hasNext()) {
		
		String command =(String)keyIter.next();
		String className = pr.getProperty(command);
		
		try {
			// �ش� ���ڿ��� Ŭ������ ����
			Class commandClass = Class.forName(className);
			//�ش�Ŭ������ ��ü�� ����
			Object commandInstance =  commandClass.newInstance();
			//map��ü�� commandMap�� ��ü ����
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

	// ������� ��û�� �м��ؼ� �ش��ϴ� �۾��� ó���ϴ� �޼ҵ�
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
