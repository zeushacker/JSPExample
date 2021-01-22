<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.net.URLEncoder" %>   
 <%-- 인코더를 활용하여 값을 저장함 --%>
 <%
 // 쿠키 생성
 Cookie cookie = new Cookie("name", URLEncoder.encode("홍길동", "utf-8"));
 
 // 응답 데이터에 쿠키를 추가
 response.addCookie(cookie);
 
 %>   
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%=cookie.getName() %> 쿠키값 =<%=cookie.getValue() %>
</body>
</html>