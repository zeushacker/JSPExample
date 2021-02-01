package mvcMem.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvcMem.control.ActionForward;

// 명령어를 전달할 인터페이스
public interface Action {

	public ActionForward execute(
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException; 
	
}
