<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 우편번호 검색 </title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="#FFFFCC">
<div align="center">
   <b>우편번호 찾기</b>
  <form action="zipCheck.jsp" method="post" name="zipForm">
  
  <table>
      <tr>
             <td> 동이름 입력 : <input type="text" name="dong">
               <input type="button" value="검색" onclick="#">
             </td>
      </tr>
  </table>
  </form>
  
  <table>
                    <tr><td align="center">
                        <a href="javascript:this.close()">닫기</a>
                    </td>
                    </tr>
  </table>
  
  </div>

</body>
</html>