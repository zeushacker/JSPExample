<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<%

   Class.forName("oracle.jdbc.driver.OracleDriver");
   Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null;
   
   String id ="",
		   passwd="",
		   name="",
		   mem_num1="",
		   mem_num2="",
		   e_mail="",
		   phone="",
		   zipcode="",
		   address="",
		   job ="";
   
   int counter = 0;
   String url="jdbc:oracle:thin:@localhost:1521:orcl";
   String user="scott";
   String password="tiger";
   try {
	   
	conn = DriverManager.getConnection(
			url,
			user, password);
	
	stmt = conn.createStatement();
	
	rs = stmt.executeQuery("select * from tempmember");
	  

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> JSP 에서 데이터베이스 연동 </title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffcc" border="1">
<h2>JSP 스크립트릿에서 데이터 베이스 연동 </h2>
<h3>회원정보</h3>
<table bordercolor="#0000ff" border="1">
<tr>
<td><strong>ID</strong></td>
<td><strong>PASSWD</strong></td>
<td><strong>NAME</strong></td>
<td><strong>MEM_NUM1</strong></td>
<td><strong>MEM_NUM2</strong></td>
<td><strong>E_MAIL</strong></td>
<td><strong>PHONE</strong></td>
<td><strong>ZIPCODE/ADDRESS</strong></td>
<td><strong>JOB</strong></td>
</tr>
<%
    if(rs != null) {
    	while(rs.next()) {
    		id=rs.getString("id");
    		passwd=rs.getString("passwd");
    		name=rs.getString("name");
    		mem_num1= rs.getString("mem_num1");
    		mem_num2= rs.getString("mem_num2");
    		e_mail= rs.getString("e_mail");
    	    phone= rs.getString("phone");
    		zipcode=rs.getString("zipcode");
            address = rs.getString("address");
            job= rs.getString("job");
%>
<tr>
  <td><%=id %></td>
  <td><%=passwd %></td>
<td><%=name %></td>
<td><%=mem_num1 %></td>
<td><%=mem_num2 %></td>
<td><%=e_mail %></td>
<td><%=phone %></td>
  <td><%=zipcode%>/<%=address %></td>
<td><%=job %></td>
<%
         counter++;
    	}// end while
    } //end if
%>
</tr>
</table><br><br>
total records : <%=counter %>

<%
   }catch(SQLException ss) {
	   ss.printStackTrace();
	}catch(Exception ee){
		ee.printStackTrace();
	}finally {
		if(rs !=null) try {rs.close();}catch(SQLException ss) {}
		if(stmt !=null) try {stmt.close();}catch(SQLException ss) {}
		if(conn !=null) try {conn.close();}catch(SQLException ss) {}
	}
%>

</body>
</html>