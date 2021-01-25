<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="boardmvc.BoardDAO" %>
<%@ page import="java.sql.Timestamp" %>

<% request.setCharacterEncoding("utf-8"); %>
<%-- 빈으로 데이터베이스 처리 --%>
<jsp:useBean id="article" class="boardmvc.BoardVO">
     <jsp:setProperty name="article" property="*" />
</jsp:useBean>

<%
 
    article.setRegdate(new Timestamp(System.currentTimeMillis()));
    article.setIp(request.getRemoteAddr());
    
    BoardDAO dbPro = BoardDAO.getInstance();
    
     dbPro.insertArticle(article);
     
     response.sendRedirect("list.jsp");

%>
