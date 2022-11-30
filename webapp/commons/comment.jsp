<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="lmc.model.Comment" %>
<%@ page import="lmc.util.TimeFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/23
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Long commentId = Long.valueOf(request.getParameter("commentId"));
    Comment comment = ((Map<Long, Comment>) request.getAttribute("commentMap")).get(commentId);
    request.setAttribute("comment", comment);
%>
<div class="media">
    <div class="media-left media-top" style="min-width: 42px">
        <img src="img/photos/${comment.creator.photo}" class="img-circle" style="width:32px">
    </div>
    <div class="media-body">
        <p><strong>${comment.creator.nickname}</strong>
            <em>${comment.ipAddress}</em>
            <span class="pull-right">
                <button onclick="like(${comment.id})" class="btn btn-link"><span id="like-num-${comment.id}">${comment.likeNum > 0 ? comment.likeNum : '赞'}</span> <span id="like-icon-${comment.id}" class="glyphicon glyphicon-thumbs-up <c:if test="${not empty cookie['comment-like-'.concat(comment.id)]}">alert-danger</c:if>"></span></button>
                <button onclick="dislike(${comment.id})" class="btn btn-link"><span id="dislike-num-${comment.id}">${comment.disLikeNum > 0 ? comment.disLikeNum : '踩'}</span> <span id="dislike-icon-${comment.id}" class="glyphicon glyphicon-thumbs-down <c:if test="${not empty cookie['comment-dislike-'.concat(comment.id)]}">alert-danger</c:if>"></span></button>
            </span>
        </p>
        <p>
            ${comment.content}
        </p>
        <p id="comment-${comment.id}">
           <span class="text-info"><%=TimeFormat.getInterval(comment.getPubDate())%></span>
            <span class="pull-right"><button class="btn-link btn-reply" onclick="reply(${comment.id})" id="btn-${comment.id}">回复</button></span>
        </p>
        <hr>
        <c:forEach items="${comment.replies}" var="reply">
            <jsp:include page="comment.jsp">
                <jsp:param name="commentId" value="${reply.id}"/>
            </jsp:include>
        </c:forEach>
    </div>
</div>
