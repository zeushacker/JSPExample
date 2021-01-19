<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="color.jsp" %> 
    
    <%
    
    String id="";
    
    try {
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null)
    	{
    		
    		for(int i = 0; i<cookies.length;i++) {
    			if(cookies[i].getName().equals("memId")){
    				id = cookies[i].getValue();
    			}
    		}
    		if(id.equals("")) {
    			response.sendRedirect("cookieMemberLogin.jsp");
    		}
    	}else {
    		response.sendRedirect("cookieMemberLogin.jsp");
    	}
    }catch(Exception e){ }

 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 쿠키를 이용한 사용자 인증 </title>
<link type="text/css" rel="stylesheet" href="style.css">
</head>
<body bgcolor="<%=bodyback_c%>">
<form action="cookieLogOut.jsp" method="post">
<table width="300" border="1" align="center">
      <tr>
             <td align="center" bgcolor="<%=value_c%>">
                        <b><%=id %></b>님이 로그인 하셨습니다.
             </td>
      </tr>
      <tr>
        <td align="center" bgcolor="<%=value_c%>">
             <input type="submit" value="로그아웃">
        </td>
      </tr>
</table>

</form>
</body>
</html>