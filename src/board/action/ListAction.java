package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

// 글 목록을 처리하는 클래스
@SuppressWarnings("deprecation")
public class ListAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		
		// 페이지 번호
		String pageNum =request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum ="1";
		}
		
		// 한페이지의 글의 개수
		int pageSize = 5;
		//  현재 페이지 
		int currentPage = Integer.parseInt(pageNum);
		
		// 한 페이지의 시작 글 번호
		int startRow = (currentPage -1) * pageSize + 1;
		// 한 페이지의 마지막 글번호
		int endRow = currentPage * pageSize;
		
		int count= 0;
		int number = 0;
		
		List<BoardVO> articleList = null;
		
		// 디비연동
		BoardDAO dbPro = BoardDAO.getInstance();
		
		// 전체 글의 수 가져다가 count 에 저장
		count = dbPro.getArticleCount();
		
		if(count > 0) {// 글의 개수가 있다면
			articleList = dbPro.getArticles(startRow, endRow);
		}else { // 글이 없다면
			articleList = Collections.emptyList();			
		}
		
		// 글 목록에 표시할 글 번호
		number = count -(currentPage -1) * pageSize;
		
		
		// 해당뷰에서 사용할 속성을 저장
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		return "/board/list.jsp";
	}

}
