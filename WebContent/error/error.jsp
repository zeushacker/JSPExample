<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
  <!-- isErrorPage : jsp 페이지에서 Throwable 객체인 
         exception 를 사용할 것인지 정하는 속성이다. -->  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외 발생</title>
</head>
<body>
요청 처리 과정에서 예외가 발생 하였습니다. <br><br>
빠른 시간내에 문제를 해결하도록 하겠습니다.
<br><br>
에러 타입   : <%= exception.getClass().getName() %><br><br>
에러 메시지 : <b> <%=exception.getMessage() %></b>

</body>
</html>