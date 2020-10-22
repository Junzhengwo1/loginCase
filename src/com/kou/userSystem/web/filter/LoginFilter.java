package com.kou.userSystem.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author dell
 *
 * 过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //先是强制转换
        HttpServletRequest request=(HttpServletRequest) req;
        //获取资源请求路径
        String uri = request.getRequestURI();
        //1.判断是否是登录相关的资源,需要注意排除 一些网页资源
        if(uri.contains("/login.jsp")||uri.contains("loginServlet")||uri.contains("/fonts")||uri.contains("/js")||uri.contains("/css")||uri.contains("/checkCodeServlet")){
            //证明用户就是想登录就放行
            chain.doFilter(req, resp);
        }else {
            //不包含，需要验证用户是否登录
            Object user = request.getSession().getAttribute("user");
            if(user!=null){
                chain.doFilter(req,resp);
            }else {
                //跳转到登录页面
                request.setAttribute("login_msg","你还没有登录，请先登录");

                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }

    }

    @Override
    public void destroy() {
    }

}
