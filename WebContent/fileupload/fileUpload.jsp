<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.oreilly.servlet.MultipartRequest" %>    
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>    
<%@ page import="java.util.*"  %> 
<%

  String uploadPath = request.getRealPath("upload");
  int size = 10 *1024*1024;//  10mb
  String name="";
  String subject ="";
  String filename1="";
  String filename2="";
  String origfilename1="";
  String origfilename2="";
  
  try {
	  MultipartRequest multi = new MultipartRequest(
			  request,
			  uploadPath,
			  size,
			  "utf-8", 
			  new DefaultFileRenamePolicy());
	  
	  name = multi.getParameter("name");
	  subject = multi.getParameter("subject");
	  Enumeration files = multi.getFileNames();
	  
	  String file1 =(String)files.nextElement();
	  // 사용자가 선택한 파일명이 실제 서버에 저장될 파일명
	  filename1 = multi.getFilesystemName(file1);
	  // 중복처리를 하기 위해(중복된 파일(덮어쓰기 방지용))
	  origfilename1 = multi.getOriginalFileName(file1);
	
	  String file2 =(String)files.nextElement();
	  filename2 = multi.getFilesystemName(file2);
	  origfilename2 = multi.getOriginalFileName(file2);
	  
  }catch(Exception e) {
	  e.printStackTrace();
  }
%> 
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<form action="fileCheck.jsp" name="filecheck" method="post">
<input type="hidden" name="name" value="<%=name%>">
<input type="hidden" name="subject" value="<%=subject%>">
<input type="hidden" name="filename1" value="<%=filename1%>">
<input type="hidden" name="filename2" value="<%=filename2%>">
<input type="hidden" name="origname1" value="<%=origfilename1%>">
<input type="hidden" name="origname2" value="<%=origfilename2%>">

</form>
  <a href="#" onclick="javascript:filecheck.submit()">
  업로드 확인 페이지로 이동 </a>

</body>
</html>