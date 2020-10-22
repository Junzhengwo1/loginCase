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

/**
 * @author dell
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取id
        String id = request.getParameter("id");
        //2.调用Service查询
        UserService service=new UserServiceImpl();
        User user = service.findUserById(id);
        //3.将user存入request域中
        request.setAttribute("user",user);
        //4.转发到update.jsp页面中
        request.getRequestDispatcher("/update.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
