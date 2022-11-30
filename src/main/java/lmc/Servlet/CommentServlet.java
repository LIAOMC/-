package lmc.Servlet;

import lmc.dao.CommentDao;
import lmc.model.Comment;
import lmc.model.News;
import lmc.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Integer newsId=Integer.valueOf(request.getParameter("newsId"));
        String replyForId=request.getParameter("replyForId");
        String content = request.getParameter("content");
        User user = (User) request.getSession().getAttribute("user");

        Comment comment=new Comment();
        comment.setContent(content);
        News news = new News();
        news.setId(newsId);
        comment.setNews(news);
        comment.setCreator(user);
        comment.setIpAddress(request.getRemoteAddr());//获得IP地址
        if(replyForId!=null){
            Comment replyFor=new Comment();
            replyFor.setId(Long.valueOf(replyForId));
            comment.setReplyFor(replyFor);
        }
        CommentDao commentDao=new CommentDao();
        commentDao.addComment(comment);

        response.sendRedirect("news.jsp?id="+newsId);
    }
}
