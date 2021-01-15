<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버 정보 출력</title>
</head>
<body>
서버 정보 : <%=application.getServerInfo() %>
서블릿 규약의 메이저 버전 :
  <%=application.getMajorVersion() %>
서블릿 규약의 마이너 버전 : 
  <%=application.getMinorVersion() %>
</body>
</html>