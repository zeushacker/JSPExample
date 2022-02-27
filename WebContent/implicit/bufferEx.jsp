<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
       // 버퍼의 크기를 구하는 메소드
       int bufferSize = out.getBufferSize();
       // 현재 버퍼의 남은 크기를 구하는 메소드
       int remainSize=out.getRemaining();
       
       int usedSize = bufferSize-remainSize; 
       // 현재 사용한 버퍼의 크기
    %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
버퍼의 전체 크기 : <%=bufferSize %><br><br>
사용한 버퍼 크기 :<%=usedSize %><br><br>
남은 버퍼 크기 :<%=remainSize %><br><br>

</body>
</html>