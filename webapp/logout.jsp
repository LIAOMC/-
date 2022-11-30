<%--
  Created by IntelliJ IDEA.
  User: 41150
  Date: 2022-09-23
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //销毁session对象
    session.invalidate();
    //返回首页
    response.sendRedirect("index.jsp");
%>
