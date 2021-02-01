package mvcMem.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvcMem.action.Action;


public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��û ���ڵ�
		request.setCharacterEncoding("utf-8");
		// ���� ���ڵ�
		response.setContentType("text/html;charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		// ��ɾ� �Ķ����
		
		if(cmd != null) {
			//  �̱������� ��ü ����
			ActionFactory factory = ActionFactory.getInstance();
		    Action action = factory.getAction(cmd);
		    
		    ActionForward af = action.execute(request, response);
		    
		    if(af.isRedirect()) {
		    	response.sendRedirect(af.getUrl());
		    }else {
		    	RequestDispatcher rd = 
		    			request.getRequestDispatcher(af.getUrl());
		    	rd.forward(request, response);
		    }
		 }else {// cmd == null �� ���
			 PrintWriter out = response.getWriter();
			 out.println("<html>");
			 out.println("<head><title>Error</title></head>");
			 out.println("<body>");
			 out.println("<h4>http://localhost:9000/JSPExample/mvcMem/member.mdo?cmd=��ûŰ����</h4>");
			 out.println("</body>");
			 out.println("</html>");
		 }
	}
	

}
