<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="sample.SimpleData" %> 
 <%
              request.setCharacterEncoding("utf-8");
             // String message = request.getParameter("message");
              
              //SimpleData msg = new SimpleData();
 %>   
 <jsp:useBean id="msg" class="sample.SimpleData" />   
    
  <jsp:setProperty property="message" name="msg"/>  
   <%-- <%msg.setMessage("나는 조선의 의적 홍길동이다."); %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<hr color="red"><br><br>
<font size="5">

메시지 : <jsp:getProperty property="message" name="msg"/>
<%-- <%=message %> --%>
 </font>

</body>
</html>