package mvcMem.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvcMem.control.ActionForward;

// ��ɾ ������ �������̽�
public interface Action {

	public ActionForward execute(
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException; 
	
}
