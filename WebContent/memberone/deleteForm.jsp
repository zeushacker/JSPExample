<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body onload="begin()">
<form action="deleteProc.jsp" 
name="myForm" method="post" onsubmit="return checkIt()" >

<table width="260" border="1" align="center">

<tr>
     <td colspan="2" align="center">
        <b>회원탈퇴</b>
     </td>
</tr>

<tr>
         <td width="150"><b>비밀번호 입력</b> </td>
          <td width="110">
                    <input type="password" name="pass" size="15">
          </td>
</tr>

<tr>
            <td colspan="2" align="center">
                     <input type="submit" value="회원탈퇴">&nbsp;
                     <input type="button" value="취소" 
                     onclick="javascript:window.location='login.jsp'">
           </td>
</tr>


</table>

</form>
</body>
</html>