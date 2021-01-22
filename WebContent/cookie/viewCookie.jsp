<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ page import="java.net.URLDecoder" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 목록</title>
</head>
<body>

쿠키 목록 <br>

<%
   Cookie[] cookies = request.getCookies();

    if(cookies != null && cookies.length > 0) {
      // 쿠키 값이 존재하면
        for(int i = 0; i < cookies.length; i++) {
   %>
       <%=cookies[i].getName() %> = 
       <%=URLDecoder.decode(cookies[i].getValue(), "utf-8") %>
     
<br>

<%}// end for
    
    } else {// 쿠키 값이 없을 경우
    
    %>
  쿠키가 존재하지 않습니다.
  <% } %>

</body>
</html>