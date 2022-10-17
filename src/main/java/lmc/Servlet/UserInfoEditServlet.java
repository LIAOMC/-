package lmc.Servlet;


import lmc.dao.UserDao;
import lmc.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "UserInfoEditServlet", value = "/UserInfoEditServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*10,      	// 单个文件大小限制10M
        maxRequestSize=1024*1024*50)   	// 总文件大小限制 5OM
public class UserInfoEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // 防止中文文件名乱码

        String account = request.getParameter("account");
        String nickname = request.getParameter("nickname");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");

        User user = new User();

        user.setAccount(account);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setMobile(mobile);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setBirthday( df.parse(birthday.toString())); // 解析日期字符串为Date对象
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 获取头像
        Part part = request.getPart("photo");
        if(part != null) {
            String uploadFilePath = this.getServletContext().getRealPath("/img/photos");
            String fileName = part.getSubmittedFileName();
            // 写文件
            part.write(uploadFilePath + File.separator + fileName);
            user.setPhoto(fileName);
        } else {
            String photo = ((User)request.getSession().getAttribute("user")).getPhoto(); //从session中获得当前值
            user.setPhoto(photo);
        }

        //保存用户信息至数据库
        UserDao userDao = new UserDao();
        userDao.updateUser(user);
        //更新当前会话中用的户信息对象
        request.getSession().setAttribute("user", user);


        // 页面跳转
        request.setAttribute("page", "index.jsp");
        request.setAttribute("message", "个人信息修改成功。");
        request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
    }
}
