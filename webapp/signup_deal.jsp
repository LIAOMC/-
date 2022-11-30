<%@ page import="lmc.dao.UserDao" %>
<%@ page import="lmc.model.User" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="lmc.util.Md5Util" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2022/9/30
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获取参数
    request.setCharacterEncoding("UTF-8");

    String mobile = request.getParameter("mobile");
    String validateCode = request.getParameter("validateCode");
    String password = request.getParameter("password");
    String account =request.getParameter("account");
    String nickname =request.getParameter("nickname");
    String email =request.getParameter("email");
    String birthday =request.getParameter("birthday");
    String photo =request.getParameter("photo");

    UserDao userDao=new UserDao();
    String error_account =null;
    String error_mobile=null;
    String error_email=null;
    String error_nickname=null;
    if(userDao.isUserExited("account",account)){
        error_account="账户已存在";
        request.setAttribute("error_account",error_account);
    }
    if(userDao.isUserExited("mobile",mobile)){
        error_mobile="手机号码已存在";
        request.setAttribute("error_mobile",error_mobile);
    }
    if(userDao.isUserExited("nickname",nickname)){
        error_nickname="id已存在";
        request.setAttribute("error_nickname",error_nickname);
    }
    if(email!=null && userDao.isUserExited("email",email)){
        error_email="电子邮件已存在";
        request.setAttribute("error_mobile",error_email);
    }
    String sValidateCode=(String)session.getAttribute("validateCode");//从session获取已发送的验证码
    System.out.println(validateCode);
    System.out.println(sValidateCode);
    String error_validateCode=null;
    if(sValidateCode==null ||!sValidateCode.equals(validateCode)){
        error_validateCode="验证码不正确";
        request.setAttribute("error_validateCode",error_validateCode);
    }
    if(error_account==null && error_mobile==null && error_nickname==null && error_email==null && error_validateCode==null){
        User user=new User();
        user.setAccount(account);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");//字符串转换成日期格式
        Date birthdayDate=df.parse(birthday);
        user.setBirthday(birthdayDate);
        user.setMobile(mobile);
        user.setNickname(nickname);
        user.setPassword(Md5Util.md5(password));
        user.setEmail(email);
        user.setPhoto(photo);
        userDao.addUser(user);

        request.setAttribute("message","注册成功，请使用手机号码和密码进行登录。");
        request.setAttribute("page","login.jsp");
        request.getRequestDispatcher("delay_jump.jsp").forward(request,response);
    }else{
        request.getRequestDispatcher("signup.jsp").forward(request,response);
    }
%>
