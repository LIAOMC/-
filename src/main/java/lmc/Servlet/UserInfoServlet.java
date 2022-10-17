package lmc.Servlet;

import lmc.dao.UserDao;
import lmc.model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserInfoServlet", value = "/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/img/photos") ;



        File uploadDir = new File(uploadPath);

        try {
            List<FileItem> formItems = upload.parseRequest(request);

            User user = new User();
            // 迭代表单数据
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println(filePath);
                    // 保存文件到硬盘
                    item.write(storeFile);

                    user.setPhoto(fileName);
                } else {  // 处理普通表单
                    String name = item.getFieldName();
                    String value = item.getString();
                    if("account".equalsIgnoreCase(name)){
                        user.setAccount(value);
                    } else if("nickname".equalsIgnoreCase(name)){
                        value = new String(value.getBytes("ISO-8859-1"),
                                "UTF-8"); //必须进行转码，否则会出现中文乱码
                        user.setNickname(value);
                    }else if("birthday".equalsIgnoreCase(name)){
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        System.out.println(value);
                        Date birthday = df.parse(value); // 解析日期字符串为Date对象
                        user.setBirthday(birthday);
                    }else if("email".equalsIgnoreCase(name)){
                        user.setEmail(value);
                    }else if("mobile".equalsIgnoreCase(name)){
                        user.setMobile(value);
                    }
                }
            }

            if(user.getPhoto() == null) { //如果没有上传头像，使用以前头像
                String photo = ((User)request.getSession().getAttribute("user")).getPhoto(); //从session中获得当前值
                user.setPhoto(photo);
            }

            // 更新用户信息
            UserDao userDao = new UserDao();
            userDao.updateUser(user);

            //更新当前会话中用的户信息对象
            request.getSession().setAttribute("user", user);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("page", "index.jsp");
        request.setAttribute("message", "个人信息修改成功。");
        request.getRequestDispatcher("alert_jump.jsp").forward(request, response);

    }
}
