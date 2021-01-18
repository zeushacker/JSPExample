<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <jsp:useBean id="beantest" class="test.BeanTest" scope="page" />
    <%-- test 패키지에 있는 BeanTest 자바빈 클래스를 beantest 라는 
    이름으로 객체를 생성함 --%>
    
 <%--    <jsp:setProperty property="name" name="beantest" value="BeanTest"/> --%>
    
    <%-- <jsp:setProperty property="name" name="beantest" param="name"/> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<b>자바빈</b>
<h3><%=beantest.getName() %></h3>
<!-- beantest 객체에 있는 name 값을 출력함  -->
<h3><jsp:getProperty property="name" name="beantest"/></h3>
</body>
</html>