<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2022/9/30
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/main.css">
</head>
<body>
<%@include file="commons/header.jsp"%>
<div class="container container-small">
    <h1>注册
        <small>已有帐号，<a href="login.jsp">登录</a></small>
    </h1>
    <form action="signup_deal.jsp" id="signupForm" method="post">
        <div class="form-group">
            <label>手机<span class="text-danger">*</span></label>
            <input id="mobile" type="text" name="mobile" class="form-control" value="<%=request.getParameter("mobile")!=null ? request.getParameter("mobile") : ""%>">
        </div>
        <div class="form-group">
            <label>验证码<span class="text-danger">*</span></label>
            <div class="input-group">
                <input type="text" name="validateCode" class="form-control">
                <div class="input-group-btn">
                    <div class="btn btn-default" onclick="sendValidateCode()">获取验证码</div>
                </div>
            </div>
        </div>
        <%
           String error = (String)request.getAttribute("error");
        %>
        <p class="alert-danger"><%=error == null ? "": error%></p>
        <div class="form-group">
            <label>账号<span class="text-danger">*</span></label>
            <input type="text" class="form-control" name="account" placeholder="请输入账户" value="${param.account}">
            <%if(request.getAttribute("error_account") != null){%>
            <p class="text-danger"><%=request.getAttribute("error_account")%></p>
            <%}%>
        </div>
        <div class="form-group">
            <label>昵称<span class="text-danger">*</span></label>
            <input type="text" class="form-control" name="nickname" placeholder="请输入名称" value="${param.nickname}">
<%--            <c:if test="error_nickname">--%>
                <p class="text-danger">${error_account}</p>
<%--            </c:if>--%>
        </div>
        <div class="form-group">
            <label>密码<span class="text-danger">*</span></label>
            <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
        </div>
        <div class="form-group">
            <label>确认密码</label>
            <input type="password" class="form-control" name="confirmPwd" placeholder="请再次输入密码">
        </div>
        <div class="form-group">
            <label>电子邮件</label>
            <input type="email" class="form-control" name="email" placeholder="请输入电子邮件" value="${param.email}">
            <p class="text-danger">${error_email}</p>
        </div>
        <div class="form-group">
            <label>出生日期</label>
            <input type="text" class="form-control" name="birthday" id="datetimepicker" placeholder="请选择您的出生日期" value="${param.birthday}">
        </div>
        <div class="form-group">
            <label>头像</label>
            <div>
                <label class="radio-inline">
                    <input type="radio" name="photo" value="1.jpg" checked><img src="img/photos/1.jpg" class="img-circle" width="60px">
                </label>
                <label class="radio-inline">
                    <input type="radio" name="photo" value="2.jpg"><img src="img/photos/2.jpg" class="img-circle" width="60px">
                </label>
                <label class="radio-inline">
                    <input type="radio" name="photo" value="3.jpg"><img src="img/photos/3.jpg" class="img-circle" width="60px">
                </label>
                <label class="radio-inline">
                    <input type="radio" name="photo" value="4.jpg"><img src="img/photos/4.jpg" class="img-circle" width="60px">
                </label>
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">注册</button>
        </div>
        <div class="form-group">
            注册极客开发者头条即代表您同意<a href="#">极客开发者服务条款</a>
        </div>
    </form>
</div>
<%@include file="commons/footer.jsp"%>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/messages_zh.js"></script>
</body>
<script>
    function sendValidateCode() {
        window.location.href="SendValidateCodeServlet?mobile="+document.getElementById("mobile").value;  // 浏览器中实现页面跳转（相当于重新发起URL请求）
    }

    $().ready(function (){
        $('#signupForm').validate({
            rules: {
                validateCode :'required',
                account: 'required',
                nickname: 'required',
                password: {
                    required: true,
                    minlength: 6
                },
                confirmPwd: {
                    required: true,
                    equalTo: '#password'
                },
                mobile: {
                    required: true,
                    isPhone: true
                },
                email: 'email'
            },
            messages: {
                confirmPwd: {
                    equalTo: '两次密码输入不一至！'
                }
            },
            errorElement: "em",
            errorPlacement: function ( error, element ) {
                // 给错误元素增加`text-danger` class 样式
                error.addClass( "text-danger" );

                // Add `has-feedback` class to the parent div.form-group
                // in order to add icons to inputs
                element.parents( ".col-sm-5" ).addClass( "has-feedback" );

                if ( element.prop( "type" ) === "checkbox" ) {
                    error.insertAfter( element.parent( "label" ) );
                } else {
                    error.insertAfter( element );
                }

                if ( !element.next( "span" )[ 0 ] ) {
                    $( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element );
                }
            }
        });

        // 设置日期选择器
        $('#datetimepicker').datepicker({
            language: 'zh-CN', //语言
            clearBtn: true,//清除按钮
            format: "yyyy-mm-dd"//日期格式
        });
    });

    // 增加自定义手机号码验证
    $.validator.addMethod("isPhone", function(value,element) {
        var length = value.length;
        var mobile = /^1[3456789]\d{9}$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的联系电话");
</script>
</html>
