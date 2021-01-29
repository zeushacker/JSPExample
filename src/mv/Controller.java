package mv;

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ��ɾ�� ��ɾ ó���ϴ� Ŭ������ ������ ����
	private Map<String, Object> commandMap =
			new HashMap<String, Object>();
	
	// ��ɾ�� ��ɾ�ó�� Ŭ������ ���εǾ��ִ� properties������ 
	// �о Map ��ü�� CommandMap�� ������
	// ���ϸ��� Command.properties�� ����
	
	public void init(ServletConfig config) throws ServletException {
	
		// web.xml ���� �Ķ���� ���� �о��
		String props = config.getInitParameter("propertyConfig");
		
		// ��ɾ�� ��ɾ�ó�� Ŭ������ ���������� ������ Properties ��ü�� ����
		Properties pr = new Properties();
		
		// ���� ���
		String path = config.getServletContext().getRealPath("/WEB-INF");
		
		// ���� �����
		FileInputStream f = null;
		try {
			// Command.properties ������ ������ �о��
			f = new FileInputStream(new File(path, props));
			//Command.properties ������ ������ Properties ��ü ����
			pr.load(f);
			
		}catch(IOException ii) {
			throw new ServletException(ii);
		}finally {
			if(f != null) try {f.close();}catch(IOException e) {}
		}
		
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		// ��ü�� �ϳ��� ������ �� ��ü������ Properties ��ü�� ����� ��ü ������
		while(keyIter.hasNext()) {
			String command =(String)keyIter.next();
			String className = pr.getProperty(command);
			
			try {
			// �ش��ϴ� Ŭ������ ��ü�� ������
			Class commandClass = Class.forName(className);
			Object commandInstance = commandClass.newInstance();
			
			//Map ��ü�� commandMap�� ��ü�� ������
			commandMap.put(command, commandInstance);
			
			}catch(ClassNotFoundException e) {
				throw new ServletException(e);
			}catch(InstantiationException e) {
				throw new ServletException(e);
			}catch(IllegalAccessException e) {
				throw new ServletException(e);
			}
		}// end while
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	
	// ������� ��û�� �м��� �ش� �۾��� ó���ϸ� ������
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandProcess com = null;
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath())== 0) {
				command = command.substring(request.getContextPath().length());
			}
			com =(CommandProcess)commandMap.get(command);
			view =com.requestPro(request, response);
			
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(view);
		
		 dispatcher.forward(request, response);
		
	}


}
