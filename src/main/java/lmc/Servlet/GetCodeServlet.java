package lmc.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lmc.util.CheckCodeUtil;

import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "getCodeServlet",value = "/getCodeServlet")
public class GetCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
