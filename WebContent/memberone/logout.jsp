<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
   
    // 세션값을 무효화 시킴 (없애준다.)
     session.invalidate();
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<font size="5">
성공적으로 로그 아웃 되었습니다. <br><br>
<a href="login.jsp">로그인 페이지로 이동</a>
</font>

</body>
</html>