<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%--  <jsp:param> 액션 태그  : 파라미터 값을 전달할때 사용하는 태그
           형식 :
           <jsp:forward page="이동할 페이지">
             <jsp:param name="파라미터 변수" value="값" />
            </jsp:forward>
    --%>
 <%

  String code = request.getParameter("code");
  String viewPageURI=null;
  
  if(code.equals("A")){
	  viewPageURI ="viewModule/a.jsp";
  }else if(code.equals("B")){
	  viewPageURI ="viewModule/b.jsp";
  }else if(code.equals("C")){
	  viewPageURI ="viewModule/c.jsp";
  }

%>

<jsp:forward page="<%= viewPageURI%>" />

