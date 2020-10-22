package com.kou.userSystem.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dell
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    //敏感词汇的集合
    
    private List<String> list =new ArrayList<String>();
    @Override
    public void init(FilterConfig config) throws ServletException {
        //加载资源
        //获取文件真实路径
        ServletContext servletContext = config.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/classes/senstiveword.txt");
        //读取文件
        try {
            BufferedReader br=new BufferedReader(new FileReader(realPath));
            //将文件的每一行数据添加到list集合中。
            String line=null;
            while ((line=br.readLine())!=null){
                list.add(line);
            }
            br.close();
            System.out.println(list);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletRequest  proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //增强getParameter方法
                //判断是否是getParameter方法
                if(method.getName().equals("getParameter")){
                    //增强返回值
                    //获取返回值
                    String value = (String) method.invoke(req, args);

                    if(value!=null){
                        for(String str:list){
                            if(value.contains(str)){
                                value=value.replaceAll(str,"***");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req,args);
            }
        });
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

}
