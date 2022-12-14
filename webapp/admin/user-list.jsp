<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/30
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会员管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
<%@include file="commons/navbar.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="commons/menu.jsp"%>
        <div class="main">
            <div class="span6">  <!-- 面包屑导航 -->
                <ul class="breadcrumb">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/">首页</a> <span class="divider"></span>
                    </li>
                    <li class="active">会员管理</li>
                </ul>
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">会员列表 &nbsp;<i class="fa fa-book pull-right" aria-hidden="true"></i></h3>
                </div>
                <div class="panel-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>账户</th>
                            <th>昵称</th>
                            <th>手机号</th>
                            <th>是否禁用</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userList}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.account}</td>
                                <td>${user.nickname}</td>
                                <td>${user.mobile}</td>
                                <td>${user.forbidden?"是":"否"}</td>
                                <td><a href="${pageContext.request.contextPath}/admin/user?method=forbidden&id=${user.id}&forbidden=${!user.forbidden}">${user.forbidden?"激活":"禁用"}</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div> <!-- /container -->
<%@include file="./commons/footer.jsp"%>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
