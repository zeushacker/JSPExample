<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="memberone.StudentDAO" />    
    
  <%
      String id = request.getParameter("id");
     boolean check = dao.idCheck(id);
  
  %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복체크</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="#FFFFCC">
<br>
<div align="center">
    <b><%=id %></b>
   <%
       if(check) {
    	   out.println("이미 존재하는 아이디 입니다.<br><br>");
       }else {
    	   out.println("는 사용 가능한 아이디 입니다.<br><br>");
       }
   %>
  
  <a href="#" onclick="javascript:self.close()">닫기</a>
   
</div>
</body>
</html>