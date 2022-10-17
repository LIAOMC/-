package lmc.Servlet;

import lmc.dao.UserDao;
import lmc.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account=request.getParameter("account");
        String password=request.getParameter("password");

        UserDao userDao=new UserDao();
        User user=userDao.getUser(account,password);

        if(user!=null){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义新的日期格式
            //format():将给定的 Date 格式化为日期/时间字符串。即：date--->String
            String dateString = formatter.format(user.getBirthday());
            HttpSession session = request.getSession();
            session.setAttribute("birthday",dateString);
            session.setAttribute("user",user);//将账户信息写入session

            response.sendRedirect("index.jsp");//重定向到首页，实现页面跳转
        }else{
            request.setAttribute("error","账户或密码错误");//将数据写入request对象
            request.getRequestDispatcher("login.jsp").forward(request,response);//前转Forward实现页面跳转
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
