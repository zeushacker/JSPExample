<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="memberone.*" %>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="dao" class="memberone.StudentDAO" />
<jsp:useBean id="vo" class="memberone.StudentVO">
     <jsp:setProperty name="vo" property="*" />
</jsp:useBean>
<%
       String loginID =(String)session.getAttribute("loginID");
       vo.setId(loginID);
       dao.updateMember(vo);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Refresh" content="3;url=login.jsp">
<title></title>
</head>
<body>

<center>
   <font size="5" face="궁서체">
   입력하신 내용대로 <b>회원정보가 수정되었습니다.</b><br><br>
   3초 후에 Login Page 로 이동합니다.
   </font>

</center>

</body>
</html>