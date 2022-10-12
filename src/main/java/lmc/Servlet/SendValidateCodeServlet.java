package lmc.Servlet;


import lmc.util.RandomNumberUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SendValidateCodeServlet")
public class SendValidateCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mobile = request.getParameter("mobile");

        String validateCode= RandomNumberUtil.getRandomNumberStr();
        request.getSession().setAttribute("validateCode",validateCode);

        System.out.println("验证码："+ mobile+"手机号码："+ validateCode);
        request.getRequestDispatcher("signup.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
