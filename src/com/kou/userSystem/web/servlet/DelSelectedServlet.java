package com.kou.userSystem.web.servlet;

import com.kou.userSystem.service.UserService;
import com.kou.userSystem.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dell
 */
@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取所有id
        String[] ids = request.getParameterValues("uid");
        //2.调用service删除
        UserService service=new UserServiceImpl();
        service.delSelectedUser(ids);
        //3.跳转到查询所有的列表
        response.sendRedirect(request.getContextPath()+"/userListServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
