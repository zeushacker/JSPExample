<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberone.*" %>    
 
 <jsp:useBean id="dao" class="memberone.StudentDAO" />
     
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Refresh" content="3;url=login.jsp">
<title>회원탈퇴</title>
</head>
<%
          String id = (String)session.getAttribute("loginID");
          String pass = request.getParameter("pass");
          int check = dao.deleteMember(id, pass);// 1,0,-1
          
          if(check ==1) {
          session.invalidate();
  %>
<body>
<div align="center">
<font size="5" face="궁서체">
   회원정보가 삭제 되었습니다. <br><br>
   아쉬움이 남지만 다음에 또 뵙기를 바라며 안녕히 가십시요.<br><br>
   3초후 로그인 페이지로 이동합니다. 
</font>
</div>

<%} %>

</body>
</html>