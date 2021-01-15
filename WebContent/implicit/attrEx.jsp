<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    /* 속성 API : ServletContext, ServletRequest, HttpSession, PageContext
                     네 가지 속성의  생존 범위영역 를 알아봄 
    */
    
    // pageContext Scope 에 속성을 저장
    pageContext.setAttribute("pageAttribute", "홍길동");
    //   pageContext.setAttribute("pageAtrribute", "홍길동",
   // 	  	PageContext.PAGE_SCOPE);
    
    
    // request Scope 에 속성을 저장
    request.setAttribute("requestAttribute", "010-1234-1234");
    // pageContext.setAttribute("requestAttribute", "010-1234-1234", 
    //		PageContext.REQUEST_SCOPE);
    
       
    // session Scope 에 속성을 저장
    session.setAttribute("sessionAttribute", "hong@naver.com");
    // pageContext.setAttribute("sessionAttribute", "hong@naver.com", 
    //	  PageContext.SESSION_SCOPE);
    
    
    // application Scope 에 속성을 저장
     application.setAttribute("applicationAttribute", "Global");
    //  pageContext.setAttribute("applicationAttribute", "Global", 
    //		PageContext.APPLICATION_SCOPE);
    
    %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<ul>
  <li> 이름 : <%= pageContext.getAttribute("pageAttribute") %> </li>
  <li>  전화:  <%= request.getAttribute("requestAttribute") %></li>
  <li>  메일:  <%= session.getAttribute("sessionAttribute") %></li>
  <li>  회사:  <%= application.getAttribute("applicationAttribute") %></li>
</ul>
</body>
</html>