<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
  // 자바코드를 기술하여 처리하는 곳
    private int numOne = 0;
    
     public void   jspInit() {// 메소드를 재정의
    	 System.out.println("jspInit() 호출");
     }


     public void   jspDestroy() {// 메소드를 재정의
    	 System.out.println("jspDestroy() 호출");
     }

%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

</head>
<body>

<%
               // service    새로고침할때마다 새로운 결과출력
               
                      int numTwo = 0;
                      numOne++;// 새로고침할때마다 증가함
                      numTwo++;// 새로고침할때마다 초기화함

%>
<ul>
    <li> Number One : <%=numOne %></li>
    <li> Number Two : <%=numTwo %></li>
</ul>

</body>
</html>