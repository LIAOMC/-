<%--
  Created by IntelliJ IDEA.
  User: 41150
  Date: 2022-09-09
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>你好！</h1>
<form action="UserServlet" method="post">
    <input type="email">
    <img src="/checkCodeServlet">
<%--    <img src="img/1-1.jpg">--%>
</form>

</body>
<script>
    alert("<%=request.getAttribute("info")%>")
</script>
</html>
