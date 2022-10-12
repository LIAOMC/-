<%@ page import="lmc.dao.CategoryDao" %>
<%@ page import="java.util.List" %>
<%@ page import="lmc.model.Category" %>
<%@ page import="lmc.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: 41150
  Date: 2022-09-21
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a href="index.html" class="navbar-brand"></a>
        </div>
        <!-- class="visible-xs-inline-block"：在超小屏幕上显示-->
        <label for="toggle-checkbox" id="toggle-label" class="visible-xs-inline-block">菜单</label>
        <input type="checkbox" class="hidden" id="toggle-checkbox">
        <div class="hidden-xs">
            <ul class="nav navbar-nav">
                <%
                    String category = request.getParameter("category"); // 获取新闻分类参数
                    String tag = request.getParameter("tag"); //获取新闻标签参数

                    // 调用Dao，获取新闻分类列表
                    List<Category> categoryList= new CategoryDao().getAllCategories();

                    //获取session中保存的账户信息
                    User user = (User) session.getAttribute("user");
                %>
                <li <%if(category==null){%> class="active"<%}%>><a href="index.jsp">首页</a></li>

                <% for(Category c: categoryList){%>
                <li <%if(category!=null && c.getId() == Integer.parseInt(category)){%>class="active"<%}%>><a href="index.jsp?category=<%=c.getId()%>"><%=c.getName()%></a></li>
                <%}%>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <%if(user==null){%>
                <li><a href="login.jsp">登录</a></li>
                <%}else{%>
                <li><a href="#"><img class="img-circle" width="30px" height="30px" src="img/photos/<%=user.getPhoto()%>"></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">${sessionScope.user.nickname}<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="userinfo.jsp">个人信息</a></li>
                        <li><a href="change_password.jsp">修改密码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="javascript:logout()">退出</a></li>
                    </ul>
                </li>
                <%}%>
                <li><a href="signup.jsp">注册</a></li>
            </ul>
        </div>
    </div>
</div>
<script>
    function logout(){
        var result=confirm('你确定要退出系统？');
        if(result){
            window.location.href="logout.jsp";
        }
    }
</script>
