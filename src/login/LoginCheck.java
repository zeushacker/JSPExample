package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		/*  db에서 사용자 정보를 조회해서 아이디 와 비밀번호를 가져와야함
		 *   가져온 아이디와 비밀번호가 일치하는지를 체크
		 *   일치할 경우 사용자 정보를 HttpSession 객체에 저장시켜서 유지함
		 */
		
		String dbID ="test";
		String dbPWD ="1234";
		
		// db에서 가져온 아이디와 사용자가 입력한 아이디를 비교
		
		if(dbID.equals(id) && dbPWD.equals(pwd)) {
			// 아이디와 비밀번호가 일치한경우
			// 세션객체를 생성
			HttpSession session = request.getSession();
			// 사용자의 정보를 세션에 저장
			session.setAttribute("user", id);
		
		}
		
		response.sendRedirect("Login");
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
