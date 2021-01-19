<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

  Cookie[] cookies  = request.getCookies();

   if(cookies != null) {// 쿠키가 존재하면
	   for(int i =0; i<cookies.length; i++) {
		   if(cookies[i].getName().equals("memId")) {// 현재 쿠키의 이름이 같다면
			   cookies[i].setMaxAge(0); // 바로 제거
			   response.addCookie(cookies[i]);
		   }
	   }
  }

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<script type="text/javascript">
alert("로그 아웃 되었습니다.");
 location.href="cookieLogInConfirm.jsp";
</script>

</body>
</html>