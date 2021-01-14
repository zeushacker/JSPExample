<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<%@ page buffer="1kb" autoFlush="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%-- <%
    Calendar cal = Calendar.getInstance();
%>

오늘은 <%=cal.get(cal.YEAR) %> 년
         <%=cal.get(cal.MONTH)+1 %> 월
         <%=cal.get(cal.DATE) %> 일
입니다. --%>

<!-- 4KB 이상의 데이터를 발생시키면 어떠한 현상이 일어날까 ?? -->

<%
for(int i =0; i<1000; i++) {
%>
1234
<%
}
%>	




</body>
</html>