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

	// ���⼭ ���� �Խ��� �۾��� �ϳ��ϳ� �޼ҵ�� �����Ͽ� ó���ϸ� ��
	
	// ������ �����ͺ��̽��� �����͸� �߰� �ϴ� �޼ҵ�
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
			
			if(num != 0) {// �亯�� �� ���
				sql ="update board set step=step+1 where ref=? and step > ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				
				pstmt.executeUpdate();
				
				step = step +1;
				depth = depth+ 1;
				
			}else {// ������ ���
				ref= number;
				step=0;
				depth=0;
			}
			
			// ���� ��� �߰��ϴ� �߰��ϴ� �����ۼ�
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
	
	// writePro.jsp ���������� �����ͺ��̽��� �Է�ó���� �ٷ� ����� �������� ó��
	// list.jsp �������� ������ ó����
//   ��ü ���� ������ ������  �޼ҵ� 
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
	
// ���̺��� ���� �����͸� ������ �޼ҵ� ����
// List
// start : ���۹�ȣ, end : ����ȣ
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

// �� ���뺸�� ȭ��
// �� ������ ������ �� ������ ���� �ִ� �۾�
// num�� �Ű������� �ؼ� �ϳ��� �ۿ� ���� �������� �����ͺ��̽����� ������

public BoardVO getArticle(int num) {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	BoardVO article = null;
	
	try {
		
		conn = ConnUtil.getConnection();
		
		pstmt = conn.prepareStatement(
				"update board set readcount = readcount+1 where num=?");
		   // ������ Ŭ���ϸ� ��ȸ�� ����
		
		pstmt.setInt(1, num);
		pstmt.executeUpdate();
		
		// num �� �ش��ϴ� ���� ��ȸ��
		pstmt = conn.prepareStatement("select * from board where num=?");
		
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if(rs.next())  {// ResultSet ���� �����ͼ� vo�� ������
			
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

// �󼼺��⿡�� �� ���� ��ư�� ������ �Ǹ� ������������ �̵�
// �Խ��ǿ� ������ �Խù��� ��ȣ�� �������� �Ͽ� ������
public BoardVO updateGetArticle(int num) {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	BoardVO article = null;
	
	try {
		
         conn = ConnUtil.getConnection();
		
		pstmt = conn.prepareStatement(
				"select * from board where num=?");
		   // ������ Ŭ���ϸ� ��ȸ�� ����
		
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


   // �� ���� ó�� (�޼ҵ� ����)
  // ��񿡼� ���� ������ ������ �Ǿ����   �� ���� ó�� �޼ҵ� ����
  public int updateArticle(BoardVO article) {
	  
	  Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		String sql="";
		int result = -1; // ��� ����  , 1: ��������, 0: ���� ����
	  
		 try {
			 conn = ConnUtil.getConnection();
			 pstmt = conn.prepareStatement("select pass from board where num=?");
			 pstmt.setInt(1, article.getNum());
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 // ��й�ȣ �� 
				 dbpasswd = rs.getString("pass");
				 
				 if(dbpasswd.equals(article.getPass())) {
					 // ��й�ȣ�� ������ ����ó��
					 sql ="update board set writer=?, email=?, "
					 		+ "subject=?, content=? where num=?";
					 
					 pstmt = conn.prepareStatement(sql);
					 pstmt.setString(1, article.getWriter());
					 pstmt.setString(2, article.getEmail());		 
					 pstmt.setString(3, article.getSubject());		 
					 pstmt.setString(4, article.getContent());
					 pstmt.setInt(5, article.getNum());
					 pstmt.executeUpdate();
					 
					 result = 1; //��������
				 }else {
					 result = 0;//��й�ȣ ����
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

	
  // �� ����ó��
  // �����ͺ��̽����� ��й�ȣ�� �˻��ؼ� ���� ��й�ȣ�� ���� �Է��� ��й�ȣ�� �´���
  // ���ϰ� ������ ����ó�� Ʋ���� ��й�ȣ ����   -1 : ����, 0: ��й�ȣ ���� , 1: ����
  
  public int deleteArticle(int num, String pass) {
	  
	  Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		String sql="";
		int result = -1; // ��� ����  , 1: ��������, 0: ���� ����
	  
		 try {
			 conn = ConnUtil.getConnection();
			 pstmt = conn.prepareStatement("select pass from board where num=?");
			 pstmt.setInt(1, num);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 // ��й�ȣ �� 
				 dbpasswd = rs.getString("pass");
				 
				 if(dbpasswd.equals(pass)) {
					 // ��й�ȣ�� ������ ����ó��
					 sql ="delete from board where num=?";
					 
					 pstmt = conn.prepareStatement(sql);
					 pstmt.setInt(1, num);
					
					 pstmt.executeUpdate();
					 
					 result = 1; //���� ����
				 }
				 else 
				 result = 0;//��й�ȣ ����
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
