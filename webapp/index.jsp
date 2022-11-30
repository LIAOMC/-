<%@ page import="lmc.dao.TagDao" %>
<%@ page import="lmc.model.Tag" %>
<%@ page import="java.util.List" %>
<%@ page import="lmc.dao.NewsDao" %>
<%@ page import="lmc.model.News" %>
<%@ page import="lmc.util.TimeFormat" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2022/9/9
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>极客开发者</title>
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
</head>
<body>
<%@include file="commons/header.jsp"%>
<div class="container">
  <div class="row">
    <div class="col-sm-2">
      <div class="list-group side-bar hidden-xs">
        <%--        <a href="#" class="list-group-item active">综合</a>--%>
        <%
          TagDao tagDao=new TagDao();
          List<Tag> tagList = tagDao.getAllTags();
          for (Tag t: tagList) {%>
        <a href="index.jsp?tag=<%=t.getId()%>" class="list-group-item<%if(tag!=null && Integer.parseInt(tag)==t.getId()){%> active<%}%>"><%=t.getName()%></a>
        <%}%>

      </div>
    </div>
    <div class="col-sm-7">
      <div class="news-list">
        <%
          //获取新闻分类参数


          NewsDao newsDao = new NewsDao();
          List<News> newsList;
          if(category != null) { //有新闻分类参数传入
            newsList = newsDao.getNewsByCategoryId(Integer.parseInt(category));
          } else if( tag != null ) {
            newsList = newsDao.getNewsByTagId(Integer.parseInt(tag));
          }
          else { // 获取置顶（首页）新闻列表
            newsList = newsDao.getAllStickNews();
          }

          for(News news: newsList) {
        %>
        <div class="news-list-item clearfix">
          <div class="col-xs-5">
            <img src="img/<%=news.getImg()%>">
          </div>
          <div class="col-xs-7">
            <a href="news.jsp?id=<%=news.getId()%>" class="title"><%=news.getTitle()%></a>
            <div class="info">
              <span class="avatar"><img src="img/logo.png"></span>
              <span>王花花</span>•
              <span>25k评论</span>•
              <span><%=TimeFormat.getInterval(news.getPubdate())%></span>
            </div>
          </div>
        </div>
        <%}%>
      </div>
    </div>
    <div class="col-sm-3">
      <div class="search-bar">
        <input type="search" class="form-control" placeholder="搜一下">
      </div>
      <div class="side-bar-card flag clearfix">
        <div class="col-xs-5">
          <img src="img/1-1.jpg">
        </div>
        <div class="col-xs-7">
          <div class="text-lg">有害信息举报专区</div>
          <div>举报电话：12377</div>
        </div>
      </div>
      <%@include file="commons/news24h.jsp"%>
      </div>
    </div>
  </div>
</div>
<%@include file="commons/footer.jsp"%>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>