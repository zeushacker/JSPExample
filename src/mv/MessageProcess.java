package mv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Controller �κ��� �۾� ��ɾ ó���ϴ� ���ø� �޾Ƽ� �۾��� ó���ϴ� Ŭ����
// ��ɾ� �������̽����� �޼ҵ带 ��ӹ޾Ƽ� ó���ϴ� ��ɾ� Ŭ����
public class MessageProcess implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setAttribute("message", "��û �Ķ���ͷ� ��ɾ ����");
		return "/mvc/process.jsp";
	}

}
