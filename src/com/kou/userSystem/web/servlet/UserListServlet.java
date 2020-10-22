package com.kou.userSystem.web.servlet;

import com.kou.userSystem.domain.User;
import com.kou.userSystem.service.UserService;
import com.kou.userSystem.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author dell
 */
@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.调用userService完成查询
        UserService service=new UserServiceImpl();
        List<User> users = service.findAll();
        //2.将list存入到request域中
        request.setAttribute("users",users);
        //3.转发到一个页面list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
