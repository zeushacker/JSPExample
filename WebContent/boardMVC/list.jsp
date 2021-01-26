<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 실제 목록을 화면에 보여줄 페이지  --%> 
<%@ page import="boardmvc.BoardDAO" %>    
<%@ page import="boardmvc.BoardVO" %>
<%@ page import="java.util.List" %>    
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="view/color.jsp" %>

<%

//1
// 한 페이지에 보여줄 글 목록 수 지정
int pageSize = 10;

// 날짜 형식 지정
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

//2
String pageNum = request.getParameter("pageNum");
if(pageNum == null) {
	pageNum = "1";
}

int currentPage = Integer.parseInt(pageNum);

int startRow = (currentPage -1) * pageSize + 1;
int endRow = currentPage * pageSize;

int count = 0;
int number = 0;
  
List<BoardVO> articleList = null;
  
BoardDAO dbPro = BoardDAO.getInstance();
  
  // 전체 글 수 
  count  = dbPro.getArticleCount();
  
  // 전체 글수가 하나라도 존재하면
  if(count > 0) {
	  // 하나라도 존재하면 리스트를 출력
	  
	  articleList = dbPro.getArticles(startRow, endRow);//3
	  
  }
  
  number = count-(currentPage - 1) * pageSize;//4
  
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

<div align="center">
<b>글목록(전체 글:<%=count %>)</b>

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
               <%-- 5 --%>
               <%
                      int wid=0;
                   
                     if(article.getDepth() > 0) {
                    	 wid = 5 * (article.getDepth());
              	 %>
              	 <img  src="img/level.gif" width=<%=wid %> height="16">       
                 <img  src="img/re.gif">
                 <%} else {%>
                <img  src="img/level.gif" width=<%=wid %> height="16">
                <%} %>
               
                  <%-- 게시글의 클릭하면 페이지 이동하면서 
                                 쿼리스트리밍으로 url 출력 (상세 페이지로 이동) 
                                 페이지는 현재 페이지 
                          --%>
                  
                   <%-- 6 --%>
                   <a href="content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">
                   
                    <%=article.getSubject() %></a>
                    <%if(article.getReadcount() >=20) { %>
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
<%-- 7 --%>
<%
// 페이지 블록

if(count > 0) {
	
	int pageBlock = 5;
	int imsi = count % pageSize == 0 ? 0 : 1;
	
	int pageCount = count / pageSize + imsi;
	
	// 시작 페이지
	int startPage =(int)((currentPage -1)/pageBlock)*pageBlock+1;
	
	// 마지막 페이지
	int endPage = startPage + pageBlock - 1;
	
	// 마지막  보여줄 페이지
	if(endPage > pageCount) endPage = pageCount;
	
	// 페이지 블록을 이전과 다음 처리작업
	
	if (startPage > pageBlock) {
		%>
		<a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
  <%
	}

	for(int i =startPage; i<=endPage; i++) {
  %>		

   			<a href="list.jsp?pageNum=<%=i%>">[<%=i %>]</a>
	
	<%}// end for 
	if(endPage < pageCount) { %>
	
	<a href="list.jsp?pageNum=<%=startPage + pageBlock%>">[다음]</a>
	
<%	
	}

}
%>

</div>
</body>
</html>