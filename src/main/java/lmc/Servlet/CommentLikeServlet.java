package lmc.Servlet;


import lmc.dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/CommentLikeServlet")
public class CommentLikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentDao commentDao=new CommentDao();
        int id= Integer.valueOf(request.getParameter("id"));
        String operate=request.getParameter("operate");
        int resultNum=-1;
        if("like".equals(operate)||"unlike".equals(operate)){
            resultNum=commentDao.updateLikeNum(id,"like".equals(operate));
        }else if("dislike".equals(operate)||"undislike".equals(operate)){
            resultNum=commentDao.updateDislikeNum(id,"dislike".equals(operate));
        }
        response.setContentType("text/plain;charset=UTF-8");
        Writer writer=response.getWriter();
        writer.write(String.valueOf(resultNum));
        writer.flush();
        writer.close();

    }
}
