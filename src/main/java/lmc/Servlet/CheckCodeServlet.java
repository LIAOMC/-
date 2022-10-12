package lmc.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lmc.util.CheckCodeUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletOutputStream os = response.getOutputStream();
        OutputStream fos = new FileOutputStream("/web/img/code.jpg");
        String checkCode = CheckCodeUtil.outputVerifyImage(100,50,fos,4);
        request.getRequestDispatcher("signup.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
