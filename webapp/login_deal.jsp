<%@ page import="lmc.dao.UserDao" %>
<%@ page import="lmc.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: 41150
  Date: 2022-09-23
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String account=request.getParameter("account");
    String password=request.getParameter("password");
    UserDao userDao=new UserDao();
    User user = userDao.getUser(account, password);
    if(user!=null){
        session.setAttribute("user",user);//将账户信息写入session
        response.sendRedirect("index.jsp");//重定向到首页，实现页面跳转
    }else{
        request.setAttribute("error","账户或密码错误");//将数据写入request对象
        request.getRequestDispatcher("login.jsp").forward(request,response);//请求转发
    }

%>
