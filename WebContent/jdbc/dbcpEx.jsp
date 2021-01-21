<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ page import="java.sql.Connection" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.InitialContext" %>    
<%@ page import="javax.naming.Context" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h3>DBCP 연동 </h3>
<%
     Context initContext = new InitialContext();

//     Context envContext = (Context)initContext.lookup("java:/comp/env");
//     DataSource ds =(DataSource)envContext.lookup("jdbc/myoracle"); 
        //               initContext.lookup()
     DataSource ds =(DataSource)initContext.lookup(
    		 "java:/comp/env/jdbc/myoracle");
    // DataSource 객체를 얻기위해서 initContext 객체를 생성해야하고
    // lookup 메소드를 사용하여  DataSource 객체를 얻음
    // 매개변수는 context.xml파일에서 resouce 태그에 설정 된 이름으로 값을 지정함
     Connection conn = ds.getConnection();
        
     out.println("DBCP 연동 성공 ...");

%>
</body>
</html>