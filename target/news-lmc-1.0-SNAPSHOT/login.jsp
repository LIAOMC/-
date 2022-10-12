<%--
  Created by IntelliJ IDEA.
  User: 41150
  Date: 2022-09-23
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>登陆</title>
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
</head>
<body>
<%@include file="commons/header.jsp"%>
<div class="container container-small">
  <h1>登录
    <small>没有帐号？<a href="signup.jsp">注册</a></small>
  </h1>
  <form action="LoginServlet" method="post">
    <div class="form-group">
      <label>用户名/手机/邮箱</label>
      <input type="text" name="account" class="form-control">
    </div>
    <div class="form-group">
      <label>密码</label>
      <input type="password" name="password" class="form-control">
    </div>
    <%
      String error=(String)request.getAttribute("error");
    %>
    <p class="alert-danger"><%=error !=null ? error : ""%></p>
    <div class="form-group">
      <button type="submit" class="btn btn-primary btn-block">登录</button>
    </div>
    <div class="form-group">
      <a href="#">忘记密码？</a>
    </div>
  </form>
</div>
<%@include file="commons/footer.jsp"%>
</body>
</html>
