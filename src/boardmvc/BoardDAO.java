package boardmvc;
import java.sql.*;
import java.util.*;


public class BoardDAO {
	
	private static BoardDAO instance = null;
	private BoardDAO() { }
	
	public static BoardDAO getInstance (){
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}

	// 여기서 부터 게시판 작업을 하나하나 메소드로 구현하여 처리하면 됨
	
	// 실제로 데이터베이스에 데이터를 추가 하는 메소드
	public void insertArticle(BoardVO article) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = article.getNum();
		int ref= article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		
		int number = 0;
	
		String sql="";
		
		try {
			
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) number = rs.getInt(1)+1;
			else number = 1;
			
			if(num != 0) {// 답변글 일 경우
				sql ="update board set step=step+1 where ref=? and step > ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				
				pstmt.executeUpdate();
				
				step = step +1;
				depth = depth+ 1;
				
			}else {// 새글일 경우
				ref= number;
				step=0;
				depth=0;
			}
			
			// 실제 디비에 추가하는 추가하는 쿼리작성
			sql = "insert into board(num, writer, email, subject, "
					+ "pass, regdate, ref, step, depth, content, ip) "
					+ "values(board_seq.nextval, ?,?,?,?,?,?,?,?,?,?)"; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2,article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPass());
			pstmt.setTimestamp(5, article.getRegdate());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, step);
			pstmt.setInt(8, depth);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
			if(conn != null) try {conn.close();}catch(SQLException s) {}
		}
	}// end insertArticle
	
	// writePro.jsp 페이지에서 데이터베이스로 입력처리후 바로 목록을 응답으로 처리
	// list.jsp 페이지로 응답을 처리함
//   전체 글의 개수를 가져올  메소드 
public int getArticleCount() {

	Connection conn = null;
	PreparedStatement pstmt =  null;
	ResultSet rs = null;
	int x = 0;
	
	try {
		
		conn = ConnUtil.getConnection();
		pstmt = conn.prepareStatement("select count(*) from board");
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			x = rs.getInt(1);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(rs != null) try {rs.close();}catch(SQLException s) {}
		if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
		if(conn != null) try {conn.close();}catch(SQLException s) {}
	}
	return x ;		
}
	
// 테이블에서 실제 데이터를 가져올 메소드 구현
// List
// start : 시작번호, end : 끝번호
public List<BoardVO> getArticles(int start, int end) {//1

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<BoardVO> articleList = null;
	
	
	try {
		conn = ConnUtil.getConnection();
		//2
		//pstmt = conn.prepareStatement("select * from board order by num desc");
		pstmt = conn.prepareStatement(
				"select * from ("
				+ "select rownum rnum, num, writer, email, subject, "
				+ "pass, regdate, readcount, ref, step, depth, content, ip from ("
				+ "select * from board order by ref desc, step asc))"
				+ " where rnum > ? and num <= ? ");
	
		//3
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			articleList = new ArrayList<BoardVO>(end-start+1);//4
			
			do {
				BoardVO article = new BoardVO();
				
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
				
				articleList.add(article);
				
			}while(rs.next());
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(rs != null) try {rs.close();}catch(SQLException s) {}
		if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
		if(conn != null) try {conn.close();}catch(SQLException s) {}
	}
	
	return articleList;
}// end List (getArticles)

// 글 내용보기 화면
// 글 제목을 누르면 글 내용을 볼수 있는 작업
// num을 매개변수로 해서 하나의 글에 대한 상세정보를 데이터베이스에서 가져옴

public BoardVO getArticle(int num) {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	BoardVO article = null;
	
	try {
		
		conn = ConnUtil.getConnection();
		
		pstmt = conn.prepareStatement(
				"update board set readcount = readcount+1 where num=?");
		   // 제목을 클릭하면 조회수 증가
		
		pstmt.setInt(1, num);
		pstmt.executeUpdate();
		
		// num 에 해당하는 글을 조회함
		pstmt = conn.prepareStatement("select * from board where num=?");
		
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if(rs.next())  {// ResultSet 에서 가져와서 vo에 저장함
			
			article = new BoardVO();
			article.setNum(rs.getInt("num"));
			article.setWriter(rs.getString("writer"));
			article.setEmail(rs.getString("email"));
			article.setSubject(rs.getString("subject"));
			article.setPass(rs.getString("pass"));
			article.setRegdate(rs.getTimestamp("regdate"));
			article.setReadcount(rs.getInt("readcount"));
			article.setRef(rs.getInt("ref"));
			article.setStep(rs.getInt("step"));
			article.setDepth(rs.getInt("depth"));
			article.setContent(rs.getString("content"));
			article.setIp(rs.getString("ip"));
			
		}
 	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(rs != null) try {rs.close();}catch(SQLException s) {}
		if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
		if(conn != null) try {conn.close();}catch(SQLException s) {}
	}
	
	return article;
	
}// end getArticle

// 상세보기에서 글 수정 버튼을 누르게 되면 수정페이지로 이동
// 게시판에 가져올 게시물을 번호를 조건으로 하여 가져옴
public BoardVO updateGetArticle(int num) {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	BoardVO article = null;
	
	try {
		
         conn = ConnUtil.getConnection();
		
		pstmt = conn.prepareStatement(
				"select * from board where num=?");
		   // 제목을 클릭하면 조회수 증가
		
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			article = new BoardVO();
			article.setNum(rs.getInt("num"));
			article.setWriter(rs.getString("writer"));
			article.setEmail(rs.getString("email"));
			article.setSubject(rs.getString("subject"));
			article.setPass(rs.getString("pass"));
			article.setRegdate(rs.getTimestamp("regdate"));
			article.setReadcount(rs.getInt("readcount"));
			article.setRef(rs.getInt("ref"));
			article.setStep(rs.getInt("step"));
			article.setDepth(rs.getInt("depth"));
			article.setContent(rs.getString("content"));
			article.setIp(rs.getString("ip"));
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(rs != null) try {rs.close();}catch(SQLException s) {}
		if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
		if(conn != null) try {conn.close();}catch(SQLException s) {}
	}
	
	return article;
	
   }


   // 글 수정 처리 (메소드 구현)
  // 디비에서 실제 데이터 수정이 되어야함   글 수정 처리 메소드 구현
  public int updateArticle(BoardVO article) {
	  
	  Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		String sql="";
		int result = -1; // 결과 없음  , 1: 수정성공, 0: 수정 실패
	  
		 try {
			 conn = ConnUtil.getConnection();
			 pstmt = conn.prepareStatement("select pass from board where num=?");
			 pstmt.setInt(1, article.getNum());
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 // 비밀번호 비교 
				 dbpasswd = rs.getString("pass");
				 
				 if(dbpasswd.equals(article.getPass())) {
					 // 비밀번호가 같으면 수정처리
					 sql ="update board set writer=?, email=?, "
					 		+ "subject=?, content=? where num=?";
					 
					 pstmt = conn.prepareStatement(sql);
					 pstmt.setString(1, article.getWriter());
					 pstmt.setString(2, article.getEmail());		 
					 pstmt.setString(3, article.getSubject());		 
					 pstmt.setString(4, article.getContent());
					 pstmt.setInt(5, article.getNum());
					 pstmt.executeUpdate();
					 
					 result = 1; //수정성공
				 }else {
					 result = 0;//비밀번호 오류
				 }
			 }
		 }catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();}catch(SQLException s) {}
				if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
				if(conn != null) try {conn.close();}catch(SQLException s) {}
			}
		return result;
   }

	
  // 글 삭제처리
  // 데이터베이스에서 비밀번호를 검색해서 실제 비밀번호와 폼에 입력한 비밀번호가 맞는지
  // 비교하고 맞으면 삭제처리 틀리면 비밀번호 오류   -1 : 실패, 0: 비밀번호 오류 , 1: 성공
  
  public int deleteArticle(int num, String pass) {
	  
	  Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		String sql="";
		int result = -1; // 결과 없음  , 1: 수정성공, 0: 수정 실패
	  
		 try {
			 conn = ConnUtil.getConnection();
			 pstmt = conn.prepareStatement("select pass from board where num=?");
			 pstmt.setInt(1, num);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 // 비밀번호 비교 
				 dbpasswd = rs.getString("pass");
				 
				 if(dbpasswd.equals(pass)) {
					 // 비밀번호가 같으면 수정처리
					 sql ="delete from board where num=?";
					 
					 pstmt = conn.prepareStatement(sql);
					 pstmt.setInt(1, num);
					
					 pstmt.executeUpdate();
					 
					 result = 1; //삭제 성공
				 }
				 else 
				 result = 0;//비밀번호 오류
			 }
		  
	  }catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException s) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException s) {}
			if(conn != null) try {conn.close();}catch(SQLException s) {}
		}
	return result;
  
  }// end delete
  
  
  
  
  
  
  
  
  
	
	
	
	
	
}
