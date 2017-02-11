<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p><img alt="" src="<%=path %>/showImage" width="100px" height="100px"/>${user.username} 的个人主页 <a href="<%=path %>/out" >退出</a></p>
<p>说说</p>
<c:forEach var ="post" items="${user.postList}">
   <p>${post.content}</p>
</c:forEach>



</body>
</html>