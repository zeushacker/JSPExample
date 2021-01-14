<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  /*  서버에서 응답할때의 상태를 저장  */
  response.setStatus(HttpServletResponse.SC_OK);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 에러 발생</title>
</head>
<body>
<b>요청한 페이지가 존재하지 않습니다.</b><br>
</body>
</html>