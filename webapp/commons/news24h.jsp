<%--
  Created by IntelliJ IDEA.
  User: 41150
  Date: 2022-09-23
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    NewsDao newsDao2=new NewsDao();
    List<News> hotNewsList = newsDao2.getHotNews();//获取24h热闻
%>
<div class="side-bar-card">
    <div class="card-title">24小时热闻</div>
    <div class="card-body">
        <div class="list">
            <%for(News hotNews:hotNewsList){%>
            <div class="item">
                <a class="title" href="news.jsp?id=<%=hotNews.getId()%>"><%=hotNews.getTitle()%></a>
                <div class="desc">15k阅读　1k评论</div>
            </div>
            <%}%>
        </div>
    </div>
</div>
