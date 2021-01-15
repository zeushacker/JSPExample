<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.Enumeration" %>
  <%@ page import="java.util.Map" %>
 
 <%
        request.setCharacterEncoding("utf-8");
 %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요청 파라미터 출력</title>
</head>
<body>
<b>request.getParameter() 메소드 사용</b><br><br>
 name 파라미터  = <%=request.getParameter("name") %><br><br>
 address 파라미터 = <%=request.getParameter("address") %>
 <br><br><br><br>
 
 <b>request.getParameterValues() 메소드 사용</b><br><br>
 <%  /* 스크립트릿  : 태그 */
          String[] values = request.getParameterValues("pet");
        
         if(values != null) {
        	 for(int i = 0; i<values.length; i++) {         
       	/* 자바 코드 부분 */
 %>
<!-- 브라우저로 출력  -->
  <%= values[i]%>       

<%
  /* 자바 코드 부분 */
        	 }// end for
         }// end if 
%>
<br><br><br><br>
<b>request.getParameterNames() 메소드 사용</b><br><br>
<%
       Enumeration enumData = request.getParameterNames();
        	while(enumData.hasMoreElements()){ // 요소가 존재하면
        		
        	String name 	= (String)enumData.nextElement();
%>
<%=name %>

<%
        	}
%>
<br><br><br><br>
<b>request.getParameterMap() 메소드 사용</b><br><br>
<%
     Map  paramterMap = request.getParameterMap();
     
     String[] nameParam = (String[])paramterMap.get("name");
     
     if(nameParam != null) {
    	 
%>
  name = <%=nameParam[0] %>
<%
     }
%>

</body>
</html>






