package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, 
	        IOException {
	  
		// 한글이 깨지지 않도록 인코딩작업
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			// 세션 객체를 생성함
			HttpSession session = request.getSession(false);
			
			if(session != null) {// 사용자의 정보를 가지고 있을 경우
				String sessionId = session.getId();
				// 콘솔에 출력
				System.out.println("세션 아이디:"+sessionId);
				String user = (String)session.getAttribute("user");
				
				// 로그인에 성공했을 경우
				out.println("<html>");
				out.println("<body>");
				
				//out.println("<form method='post' action='LoginCheck'>");
				out.println("<table border='1' width='300'>");
				
				out.println("<tr>");
                out.println("<td width='300' align='center'>"+
				user+"님이 로그인 하셨습니다.</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td align='center'>");
                out.println("<a href='#'>회원정보</a>");
                out.println("<a href='Logout'>로그아웃</a>");
                out.println("</td>");
				out.println("</tr>");
                out.println("</table>");
							
				out.println("</body>");
				out.println("</html>");
								
			}else {// 로그인 실패인 경우(사용자 정보가 없을 경우)
				
				out.println("<html>");
				out.println("<body>");
				
				out.println("<form method='post' action='LoginCheck'>");
				out.println("<table border='1' width='300'>");
				
				out.println("<tr>");
                out.println("<th width='100'>아이디 </th>");
                out.println("<td width='200'>&nbsp;"
                		+ "<input type='text' name='id'></td>");
                out.println("</tr>");
				
                out.println("<tr>");
                out.println("<th width='100'>비밀번호</th>");
                out.println("<td width='200'>&nbsp;"
                		+ "<input type='password' name='pwd'></td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td align='center' colspan='2'>");
                out.println("<input type='button' value='회원가입'>");
                out.println("<input type='submit' value='로그인'>");
				out.println("</td>");
                out.println("</tr>");
				
				    out.println("</table>");
					out.println("</form>");
					out.println("</body>");
					out.println("</html>");
			}
			
		
		}finally {
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
