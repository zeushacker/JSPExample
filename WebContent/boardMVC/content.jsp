<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 글 내용을 보여줄 페이지 --%>

<%@ page import="boardmvc.BoardDAO" %>    
<%@ page import="boardmvc.BoardVO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="view/color.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<%
    int num = Integer.parseInt( request.getParameter("num"));
    String pageNum = request.getParameter("pageNum");
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    try {
    	
    	BoardDAO dbPro = BoardDAO.getInstance();
    	BoardVO article = dbPro.getArticle(num);
    	
    	int ref = article.getRef();
    	int step = article.getStep();
    	int depth = article.getDepth();
%>

<body>
<%@ include file="/inc/header.jsp" %><br><br>
<center>
<form>
<table width="500" border="1" cellpadding="0" cellspacing="0"
bgcolor="<%=bodyback_c%>" align="center">

<tr height="30">
      <td width="125" align="center" bgcolor="<%=value_c%>">글번호</td>
      <td width="125" align="center" ><%=article.getNum() %></td>
      
      <td width="125" align="center" bgcolor="<%=value_c%>">조회수</td>
      <td width="125" align="center" ><%=article.getReadcount() %></td>
</tr>

<tr height="30">
      <td width="125" align="center" bgcolor="<%=value_c%>">작성자</td>
      <td width="125" align="center" ><%=article.getWriter() %></td>
      
      <td width="125" align="center" bgcolor="<%=value_c%>">작성일</td>
      <td width="125" align="center" >
      <%=sdf.format(article.getRegdate()) %></td>
</tr>

<tr height="30">
      <td width="125" align="center" bgcolor="<%=value_c%>">글 내용</td>
       <td align="left" width="375" colspan="3">
       <pre><%=article.getContent() %></pre>
       </td>
</tr>

<tr height="30">
      
   <td colspan="4" bgcolor="<%=value_c%>" align="right">
        <input type="button" value="글수정" 
       onclick="document.location.href='updateForm.jsp?num=
       <%=article.getNum()%>&pageNum=<%=pageNum%>'">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" value="글삭제" 
       onclick="document.location.href='deleteForm.jsp?num=
       <%=article.getNum()%>&pageNum=<%=pageNum%>'">
        
      <input type="button" value="글목록" 
       onclick="document.location.href='list.jsp?num=<%=pageNum%>'">
      
      </td>
</tr>


</table>
<%
}catch(Exception e){}
%>
</form>
</center>

</body>
</html>