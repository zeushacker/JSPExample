package mv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ��û �Ķ���ͷ� ��ɾ �����ϴ� ����� ���� �������̽�
public interface CommandProcess {

	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable;
	
}
