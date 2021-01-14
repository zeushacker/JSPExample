<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h2> Include 로 페이지를 추가 </h2>
<%
     String name ="Gil Dong Hong";
%>

<%@ include file="top.jsp" %>
본문의 내용을 입력<br>
홍길동<br>
그는 누구인가?<br>
그는 조선의 의적이다.<br>
활빈당의 두목이다.<br>
<%@ include file="bottom.jsp" %>
</body>
</html>