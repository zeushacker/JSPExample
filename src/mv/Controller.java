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

	// 명령어와 명령어를 처리하는 클래스를 쌍으로 저장
	private Map<String, Object> commandMap =
			new HashMap<String, Object>();
	
	// 명령어와 명령어처리 클래스가 매핑되어있는 properties파일을 
	// 읽어서 Map 객체인 CommandMap에 저장함
	// 파일명은 Command.properties로 저장
	
	public void init(ServletConfig config) throws ServletException {
	
		// web.xml 에서 파라미터 값을 읽어옴
		String props = config.getInitParameter("propertyConfig");
		
		// 명령어와 명령어처리 클래스의 매핑정보를 저장할 Properties 객체를 생성
		Properties pr = new Properties();
		
		// 파일 경로
		String path = config.getServletContext().getRealPath("/WEB-INF");
		
		// 파일 입출력
		FileInputStream f = null;
		try {
			// Command.properties 파일의 내용을 읽어옴
			f = new FileInputStream(new File(path, props));
			//Command.properties 파일의 정보를 Properties 객체 저장
			pr.load(f);
			
		}catch(IOException ii) {
			throw new ServletException(ii);
		}finally {
			if(f != null) try {f.close();}catch(IOException e) {}
		}
		
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		// 객체를 하나씩 꺼내서 그 객체명으로 Properties 객체에 저장된 객체 접근함
		while(keyIter.hasNext()) {
			String command =(String)keyIter.next();
			String className = pr.getProperty(command);
			
			try {
			// 해당하는 클래스의 객체를 생성함
			Class commandClass = Class.forName(className);
			Object commandInstance = commandClass.newInstance();
			
			//Map 객체인 commandMap에 객체를 저장함
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
	
	
	// 사용자의 요청을 분석해 해당 작업을 처리하면 마무리
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
