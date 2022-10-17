package lmc.Servlet;

import lmc.dao.UserDao;
import lmc.model.User;
import lmc.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        UserDao userDao = new UserDao();
        String account = ((User)request.getSession().getAttribute("user")).getAccount();
        User user = userDao.getUser(account, oldPassword);
        if(user!=null){
            userDao.changeUserPassword(account, Md5Util.md5(newPassword));
            request.setAttribute("page","index.jsp");
            request.setAttribute("message","修改密码成功！");
            request.getRequestDispatcher("alert_jump.jsp").forward(request,response);
        }else{
            request.setAttribute("error","当前密码(旧密码)验证不通过！");
            request.getRequestDispatcher("change_password.jsp").forward(request,response);
        }

    }
}
