<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
         String pagefile = request.getParameter("page");
           if(pagefile == null) {pagefile="newitem";}

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">

table {
  margin: auto;
  width: 960px;
  color: gray;
  border: 1px solid gray; 

}



</style>
</head>
<body>
<table>
  <tr>
     <td height="43" colspan="3" align="left">
                <jsp:include page="top.jsp" />   
     </td>
  </tr>

  <tr>
     <td width="15%" align="right" valign="top">
                <jsp:include page="left.jsp" />   
     </td>
     
     <td align="center" colspan="2">
                <jsp:include page='<%=pagefile+".jsp" %>' />   
     </td>
  </tr>
  
  <tr>
     <td width="100%" height="40" colspan="3">
     <jsp:include page="bottom.jsp" />
     </td>
  </tr>
  
  </table>

</body>
</html>