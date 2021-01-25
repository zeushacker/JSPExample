<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="view/color.jsp" %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> My Board </title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<!-- 새글과 답변글을 구분해 주어야함 -->
<%
      int num=0, ref=1, step=0, depth=0;
   // 새글 및 답변글을 작성할때 함께 사용하므로 새글과 답변글을 구분하기 위한 코드
       try {
    	   
    	   if(request.getParameter("num") != null) {
    		   num =  Integer.parseInt(request.getParameter("num"));
    		   ref =  Integer.parseInt(request.getParameter("ref"));
    		   step =  Integer.parseInt(request.getParameter("step"));
    		   depth =  Integer.parseInt(request.getParameter("depth"));
    	   }
    	   // 새글일 경우는 num = 0이라고 해서 넘겨줄 것이고 
 	// 답변글일 경우 원글(부모)의 num를 받아와서 넘겨줌 이것으로 새글과 답변글을 구분함	   
  %>
<body bgcolor="<%=bodyback_c%>">
<%@ include file="/inc/header.jsp" %>
<center><b>글쓰기</b></center><br><br>

<form action="writeProc.jsp" 
method="post" name="writeForm" onsubmit="return writeSave()">
<!-- writeProc.jsp 로 페이지를 이동할때
 num, ref, step, depth 의 값을 가지고 가야함  -->
<input type="hidden" name="num" value="<%=num%>">
<input type="hidden" name="ref" value="<%=ref%>">
<input type="hidden" name="step" value="<%=step%>">
<input type="hidden" name="depth" value="<%=depth%>">

<table width="400" border="1" cellpadding="0" cellspacing="0"
align="center" bgcolor="<%=bodyback_c %>">


<tr>
      <td align="right" colspan="2" bgcolor="<%=value_c%>">
      <a href="list.jsp">글목록</a>
      </td>
</tr>

<tr>
     <td width="70" bgcolor="<%=value_c%>" align="center">이름 </td>
     <td width="330">
     <input type="text" size="12" maxlength="12" name="writer"></td>
</tr>

<tr>
     <td width="70" bgcolor="<%=value_c%>" align="center">이메일 </td>
     <td width="330">
     <input type="text" size="30" maxlength="30" name="email"></td>
</tr>

<tr>
     <td width="70" bgcolor="<%=value_c%>" align="center">제목 </td>
     <td width="330">
     <% if(request.getParameter("num")== null){ %>
     <input type="text" size="50" maxlength="50" name="subject">
     <% } else { %>
     <input type="text" size="50" maxlength="50" name="subject" value="[답변]">
     <% } %>
     </td>
</tr>

<tr>
     <td width="70" bgcolor="<%=value_c%>" align="center">내용 </td>
     <td width="330">
     <textarea rows="13" cols="50" name="content"></textarea>
     </td>
</tr>

<tr>
     <td width="70" bgcolor="<%=value_c%>" align="center">비밀번호 </td>
     <td width="330">
     <input type="password" size="10" maxlength="10" name="pass"></td>
</tr>

<tr>
    <td colspan="2" bgcolor="<%=value_c%>" align="center">
             <input type="submit" value="글쓰기">
             <input type="reset" value="다시작성">
             <input type="button" value="목록" 
             onclick="javascript:window.location='list.jsp'"> 
    </td>
</tr>

</table>

</form>
<%}catch(Exception e){} %>

</body>
</html>