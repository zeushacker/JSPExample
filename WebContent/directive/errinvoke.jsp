<%@ page language="java" contentType="text/html; charset=UTF-8"

pageEncoding="UTF-8"%>

​

<!-- errorPage : jsp 페이지 내에서 에러가 발생했을 때 이동할 페이지의 경로를 지정하는 속성임. -->

<%@ page errorPage="/error/error.jsp" %>

​

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>파라미터 출력</title>

</head>

<body>

name 파라미터 값 : 

<%=request.getParameter("name").toUpperCase() %>

<!-- 에러 메시지가 뜨지 않음. 이유 불명. -->

</body>

</html>