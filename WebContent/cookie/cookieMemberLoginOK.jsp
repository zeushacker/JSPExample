<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="login.LogonDBBean" %>
<% request.setCharacterEncoding("utf-8"); %>
    
<%

  String id = request.getParameter("id");
  String passwd = request.getParameter("passwd");
  // 폼에 있는 아이디와 비밀번호를 가져옴
  
  // 디비연결
  LogonDBBean manager = LogonDBBean.getInstance();
  
  // DAO 에 매개변수를 넘겨줌
  int check = manager.userCheck(id, passwd);
  
  if( check == 1) {
	  
	  // 쿠키 생성
	  Cookie cookie = new Cookie("memId", id);
	  cookie.setMaxAge(60*20);// 20분
	  response.addCookie(cookie);
	  response.sendRedirect("cookieLogInConfirm.jsp");
  }else if(check ==0) {

%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<script type="text/javascript">
alert("비밀번호가 맞지 않습니다. ");
history.go(-1);
</script>
<% } else {	%>
<script type="text/javascript">
alert("아이디가 존재하지 않습니다.  ");
history.go(-1);
</script>

<%} %>

</body>
</html>