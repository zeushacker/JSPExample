<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 실제 목록을 화면에 보여줄 페이지  --%> 
    
<%@ page import="boardmvc.BoardDAO" %>    
<%@ page import="boardmvc.BoardVO" %>
<%@ page import="java.util.List" %>    
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="view/color.jsp" %>

<%
// 날짜 형식 지정
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  int count = 0;
  int number = 0;
  
  List<BoardVO> articleList = null;
  
  BoardDAO dbPro = BoardDAO.getInstance();
  
  // 전체 글 수 
  count  = dbPro.getArticleCount();
  
  // 전체 글수가 하나라도 존재하면
  if(count > 0) {
	  // 하나라도 존재하면 리스트를 출력
	  
	  articleList = dbPro.getArticles();
	  
  }
  
  number = count;
  
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">

</head>
<body bgcolor="<%=bodyback_c%>">
<%@ include file="/inc/header.jsp" %>
<center><b>글목록(전체 글:<%=count %>)</b>

<table width="700">

<tr>
       <td align="right" bgcolor="<%=value_c%>">
            <a href="writeForm.jsp">글쓰기</a>
       </td>
</tr>
</table>

<%  if(count == 0) {// 글이 없을때  %>

<table width="700" border="1" cellpadding="0" cellspacing="0">
          <tr>
              <td align="center">
              게시판에 글이 없어요.. 글을 추가하세요
              </td>
          </tr>
</table>
<%  }else { %>
<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">

<tr height="30" bgcolor="<%=value_c%>">
       <td align="center" width="50">번호</td>
       <td align="center" width="250">제목</td>
       <td align="center" width="100">작성자</td>
       <td align="center" width="150">작성일</td>
       <td align="center" width="50">조회</td>
       <td align="center" width="100">IP</td>
</tr>
           <%
           for(int i = 0; i< articleList.size(); i++) {
              BoardVO article = (BoardVO)articleList.get(i);
           %>
           <tr height="30">
                <td align="center" width="50"> <%=number-- %></td>
             
               <td width="250">
                       <%-- 게시글의 클릭하면 페이지 이동하면서 
                                 쿼리스트리밍으로 url 출력 (상세 페이지로 이동) 
                                 페이지는 현재 페이지 
                          --%>
                    <a href="content.jsp?num=<%=article.getNum()%>&pageNum=1">
                    <%=article.getSubject() %></a>
                    <%if(article.getReadcount() >= 20) { %>
                    <%-- 조회수가 20 이상일경우 이미지 출력 --%>
                    <img  src="img/hot.gif" border="0" height="16">
                    <%} %>
                    </td>
                    
                    <td align="center" width="100">
                    <a href="mailto:<%=article.getEmail()%>">
                    <%=article.getWriter() %></a>
                    </td>
                    
                    <td align="center" width="150">
                    <%=sdf.format(article.getRegdate()) %>
                    </td>
                    
                    <td align="center" width="50">
                       <%= article.getReadcount() %>
                    </td>
                    
                    <td align="center" width="100">
                    <%=article.getIp() %>
                    </td>
             </tr>
            <%}// end for  %>

</table>
<% } %>
</center>
</body>
</html>