package mv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Controller 로부터 작업 명령어를 처리하는 지시를 받아서 작업을 처리하는 클래스
// 명령어 인터페이스에서 메소드를 상속받아서 처리하는 명령어 클래스
public class MessageProcess implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setAttribute("message", "요청 파라미터로 명령어를 전달");
		return "/mvc/process.jsp";
	}

}
