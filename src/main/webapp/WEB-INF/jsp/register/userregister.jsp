<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>user register</p>
<form id="registerForm" name="registerForm" action="register?type=user&state=1"  enctype="multipart/form-data" method="post">
    <p>userName:<input type="text" name="username"></p>
  
    <p>loginName:<input type="text" name="loginName"></p>
  
    <p>realname:<input type="text" name="realname"></p>
  
    <p>password:<input type="text" name="password"></p>
  
    <p>phone:<input type="text" name="phone"></p>
  
    <p>address:<input type="text" name="address"></p>
  
    <p>sex:<input type="radio" value="1" name="sex"><input type="radio" value="0" name="sex"></p>
  
    <p>emil:<input type="text" name="emil"></p>
  
    <p>headimg:<input type="file" name="headimgx"></p>
    <input type="submit" value ="submit">
</form>
</body>
</html>