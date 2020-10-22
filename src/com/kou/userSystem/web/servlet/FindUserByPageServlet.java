package com.kou.userSystem.web.servlet;
import com.kou.userSystem.domain.PageBean;
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
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if(currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }

        if(rows==null||"".equals(rows)){
            rows="5";
        }

        //2.调用service查询
        UserService service=new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage, rows);

        /**
         * 为了看一下 是否通过页码查询出结果了
         */
        System.out.println(pb);
        //3.
        request.setAttribute("pb",pb);
        //4.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
