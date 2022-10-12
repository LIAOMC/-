<%@ page import="lmc.dao.NewsDao" %>
<%@ page import="lmc.model.News" %>
<%@ page import="java.util.List" %>
<%@ page import="lmc.util.TimeFormat" %><%--
  Created by IntelliJ IDEA.
  User: 41150
  Date: 2022-09-16
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id=request.getParameter("id");
    NewsDao newsDao=new NewsDao();
    News news=newsDao.getNewsById(Integer.parseInt(id));
    List<News> recommendedNewsList=newsDao.getRecommendedNews(Integer.parseInt(id));//获取推荐新闻
//    List<News> hotNewsList = newsDao.getHotNews();//获取24h热闻
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新闻详情</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<%@include file="commons/header.jsp"%>
<div class="container">
    <div class="col-xs-8">
        <h1 class="news-title"><%=news.getTitle()%></h1>
        <div class="news-status">25k阅读•35分钟前发布
            <div class="label label-default">教育</div>
            <div class="label label-default">情感</div>
        </div>
        <div class="news-content">
            <%=news.getContent()%>
        </div>
    </div>
    <div class="col-xs-4">
        <div class="side-bar-card">
            <div class="card-title">相关推荐</div>
            <div class="card-body">
                <div class="list">
                    <%for(News recommendedNews:recommendedNewsList){%>
                    <div class="item clearfix">
                        <div class="col-xs-5 no-padding-h"><img src="img/<%=recommendedNews.getImg()%>"></div>
                        <div class="col-xs-7">
                            <div class="title"><a href="news.jsp?id=<%=recommendedNews.getId()%>"><%=recommendedNews.getTitle()%></a></div>
                            <div class="desc">25k阅读•<%=TimeFormat.getInterval(recommendedNews.getPubdate())%></div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
        <%@include file="commons/news24h.jsp"%>
    </div>
</div>
<%@include file="commons/footer.jsp"%>
</body>
</html>
