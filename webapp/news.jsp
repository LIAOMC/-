<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lmc.dao.NewsDao" %>
<%@ page import="lmc.util.TimeFormat" %>
<%@ page import="lmc.dao.TagDao" %>
<%@ page import="java.util.*" %>
<%@ page import="lmc.dao.CommentDao" %>
<%@ page import="lmc.model.*" %>
<%@ page import="lmc.util.CommentUtil" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2022/9/16
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获取新闻编号参数
    String id = request.getParameter("id");

    // 调用DAO查询新闻
    NewsDao newsDao = new NewsDao();
    int intId = Integer.parseInt(id);
    News news = newsDao.getNewsById(intId);

    CommentDao commentDao = new CommentDao();
    List<Comment> comments = commentDao.getCommentsByNewsId(intId);
    request.setAttribute("comments",comments);
    request.setAttribute("commentMap", CommentUtil.toMap(comments));

    List<News> recommendedNewsList = newsDao.getRecommendedNews(intId); // 获取推荐新闻列表

//    List<News> hotNewsList = newsDao.getHotNews(); // 获取24小时热闻列表

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新闻详情</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/main.css">
    <link rel="stylesheet" href="css/quill.snow.css">
    <link rel="stylesheet" href="css/quill-emoji.css">
</head>
<body>
<%@include file="commons/header.jsp"%>
<div class="container">
    <div class="col-xs-8">
        <h1 class="news-title"><%=news.getTitle()%></h1>
        <div class="news-status">25k阅读•35分钟前发布
            <%
                TagDao tagDao=new TagDao();
                Set<Tag> tagByNewsId = tagDao.getTagByNewsId(news.getId());
                System.out.println(tagByNewsId.iterator().next());
                Iterator<Tag> iterator = tagByNewsId.iterator();
            %>
            <%while (iterator.hasNext()){%>
            <div class="label label-default"><%=iterator.next().getName()%></div>
            <%}%>
        </div>
        <div class="news-content">
            <%=news.getContent()%>
        </div>
        <div>
            <h3>我要评论</h3>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/CommentServlet?newsId=${param.id}" method="post"
                  onsubmit="return setContent()">
                <div id="editor">
                </div>
                <input type="hidden" name="content" id="content">
                <%--                    <input type="hidden" name="content">--%>
                <button type="submit" class="btn btn-default" <c:if test="${empty sessionScope.user}">disabled</c:if>>
                    发布
                </button><c:if test="${empty sessionScope.user}">您还未登陆，请先<a href="login.jsp">登陆</a></c:if>
            </form>
        </div>
        <h3>最新评论</h3>
        <c:forEach items="${comments}" var="comment">
            <jsp:include page="commons/comment.jsp">
                <jsp:param name="commentId" value="${comment.id}"/>
            </jsp:include>
        </c:forEach>
    </div>
    <div class="col-xs-4">
        <div class="side-bar-card">
            <div class="card-title">相关推荐</div>
            <div class="card-body">
                <div class="list">
                  <%for(News recommendedNews : recommendedNewsList) {%>
                    <div class="item clearfix">
                        <div class="col-xs-5 no-padding-h"><img src="img/<%=recommendedNews.getImg()%>"></div>
                        <div class="col-xs-7">
                            <div class="title"><a href="news.jsp?id=<%=recommendedNews.getId()%>"><%=recommendedNews.getTitle()%></a></div>
                            <div class="desc">25k阅读•<%=TimeFormat.getInterval(recommendedNews.getPubdate())%>发布</div>
                        </div>
                    </div>
                  <%}%>
                </div>
            </div>
        </div>
        <%@include file="commons/news24h.jsp"%>
<%--        <jsp:include page="commons/news24h.jsp"></jsp:include>--%>
    </div>
</div>
<%@include file="commons/footer.jsp"%>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.cookie.js"></script>
<script src="js/quill.min.js"></script>
<script src="js/quill-emoji.js"></script>
<script>
    var toolbarOptions = {
        container: [
            ['bold', 'italic', 'underline', 'strike'],
            ['blockquote', 'code-block'],
            ['emoji']
        ],
        handlers: {
            'emoji': function () {}
        }
    };

    var quill = new Quill('#editor', {
        modules: {
            "toolbar": toolbarOptions,
            "emoji-toolbar": true,
            "emoji-shortname": true,
            "emoji-textarea": true
        },
        theme: 'snow'
    });

    function setContent() {
        if(quill && quill.getLength() > 1){
            $("#content").val(quill.root.innerHTML);
            return true;
        } else{
            alert("内容不能为空！")
            return false;
        }
    }
    var replyQuill; //定义全局变量

    function setReplyContent() {
        if(replyQuill && replyQuill.getLength() > 1){
            $("#reply-content").val(replyQuill.root.innerHTML);
            return true;
        } else {
            alert("内容不能为空！")
            return false;
        }
    }

    function reply(commentId) {
        $(".editor-form").remove(); //清除编辑表单
        $(".btn-reply").removeAttr("disabled"); // 恢复按钮功能

        $("#btn-"+commentId).attr("disabled", "disabled");  //停止重复点击
        $("#comment-"+commentId).append("<form action='${pageContext.request.contextPath}/CommentServlet?newsId=${param.id}&replyForId="+commentId
            +"' class='editor-form' method='post' onsubmit='return setReplyContent()'><div id='ql-editor'></div><input type='hidden' name='content' id='reply-content'><button class='btn btn-default'"
            <c:if test="${empty sessionScope.user}">+ ' disabled'</c:if> + ">回复</button>"
            <c:if test="${empty sessionScope.user}">+ '您还未登陆，请先<a href="login.jsp">登陆</a>'</c:if> + "</form>");
        replyQuill = new Quill('#ql-editor', {
            modules: {
                "toolbar": toolbarOptions,
                "emoji-toolbar": true,
                "emoji-shortname": true,
                "emoji-textarea": true
            },
            theme: 'snow'
        });
    }
    function like(commentId) {
        var like = $.cookie('comment-like-'+commentId);  // 从Cookie中获取点赞记录
        var operate = like? 'unlike': 'like';

        $.post('${pageContext.request.contextPath}/CommentLikeServlet?id='+commentId + '&operate=' + operate, function (num) {
            // 更新点赞数和图标状态
            $('#like-num-'+commentId).text(num > 0 ? num : '赞');
            if(like){
                $('#like-icon-'+commentId).removeClass('alert-danger');
                $.removeCookie('comment-like-'+commentId, { path: "/"});  // 删除Cookie
            } else {
                $('#like-icon-'+commentId).addClass('alert-danger');
                $.cookie('comment-like-'+commentId, true, { path: "/"}); // 写入Cookie
            }
        });
    }

    function dislike(commentId) {
        var dislike = $.cookie('comment-dislike-'+commentId);  // 从Cookie中获取点踩记录
        var operate = dislike? 'undislike': 'dislike';

        $.post('${pageContext.request.contextPath}/CommentLikeServlet?id='+commentId + '&operate=' + operate, function (num) {
            // 更新点赞数和图标状态
            $('#dislike-num-'+commentId).text(num > 0 ? num : '踩');
            if(dislike){
                $('#dislike-icon-'+commentId).removeClass('alert-danger');
                $.removeCookie('comment-dislike-'+commentId, { path: "/"});  // 删除Cookie
            } else {
                $('#dislike-icon-'+commentId).addClass('alert-danger');
                $.cookie('comment-dislike-'+commentId, true, { path: "/"}); // 写入Cookie
            }
        });
    }
</script>
</body>
</html>
