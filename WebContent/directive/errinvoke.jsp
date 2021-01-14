<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page errorPage="/error/error.jsp" %> --%> 
<!--  errorPage : jsp 페이지 내에서 에러가 발생 했을때 이동할 페이지을 경로를 지정하는 속성 -->   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 파라미터 출력 </title>
</head>
<body>
name 파라미터 값 :
<%= request.getParameter("name").toUpperCase() %>
</body>
</html>