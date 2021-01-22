<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%  session.invalidate(); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<script type="text/javascript">
  alert("로그 아웃 되었습니다.");
  location.href="sessionMemberLogIn.jsp";
</script>

</body>
</html>