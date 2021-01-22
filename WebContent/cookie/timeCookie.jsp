<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    Cookie cookie = new Cookie("hour", "1time");
    cookie.setMaxAge(300);// 1분(60초)
    response.addCookie(cookie);
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유효시간 설정</title>
</head>
<body>
유효시간이 설정 된 쿠키 생성
</body>
</html>