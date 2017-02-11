<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>
<script type="text/JavaScript" src='<%=path %>/js/jquery-1.11.1.js'></script>
<script type="text/javascript">
 
</script>
</head>


<body>
  <form action="login" method="post">
    <input type ="text" name = "loginName" value="${loginName}">
    <input type="password" name ="password" value="${password}">
    <button type="submit" value="submit" >submit</button>
  </form>
  <a href="register?type=user&state=0">register</a>
  <%=basePath %>
</body>
</html>