package com.kou.userSystem.web.servlet;

import com.kou.userSystem.domain.User;
import com.kou.userSystem.service.UserService;
import com.kou.userSystem.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author dell
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        //2.1获取的用户填写的验证码
        String verifycode = request.getParameter("verifycode");
        //2.2获取全部数据
        Map<String, String[]> map = request.getParameterMap();
        //3.校验验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        //确保验证码的一次性
        session.removeAttribute("CHECKCODE_SERVER");
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            //提示信息
            request.setAttribute("login_msg","验证码错误");
            //跳转到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //4.封装user对象
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //5.调用service查询
        UserService service=new UserServiceImpl();
        User loginUser=service.login(user);
        //6.判断是否登录成功
        if(loginUser!=null){
            //登录成功
            session.setAttribute("user",loginUser);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");

        }else {
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或者密码错误");
            //跳转到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
