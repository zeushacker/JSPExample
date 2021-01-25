<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="boardmvc.BoardDAO" %>    
<%@ page import="java.sql.Timestamp" %>
<% request.setCharacterEncoding("utf-8"); %>    
<jsp:useBean id="article" class="boardmvc.BoardVO">
    <jsp:setProperty name="article" property="*" />
</jsp:useBean>    
<%
   String pageNum = request.getParameter("pageNum");
   BoardDAO dbPro = BoardDAO.getInstance();
   int check = dbPro.updateArticle(article);
   if(check == 1){ // 수정 성공
%>    
<meta charset="UTF-8" http-equiv="Refresh"
 content="0;url=list.jsp?pageNum=<%=pageNum%>">
<%}else { %>
<script type="text/javascript">
alert("비밀번호가 맞지 않습니다.");
history.go(-1);
</script>
<%} %>