package com.kou.userSystem.service.impl;

import com.kou.userSystem.dao.UserDao;
import com.kou.userSystem.dao.impl.UserDaoImpl;
import com.kou.userSystem.domain.PageBean;
import com.kou.userSystem.domain.User;
import com.kou.userSystem.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @author JIAJUN KOU
 */

public class UserServiceImpl implements UserService {
    private UserDao dao=new UserDaoImpl();


    @Override
    public List<User> findAll() {
        //调用dao来完成查询

        return dao.findAll();
    }


    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {

        //把id转换成int
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {

        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {

        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {

        //遍历数组
        for (String id : ids) {
            //调用dao删除
            dao.delete(Integer.parseInt(id));
        }

    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);

        if(currentPage<=0){
            currentPage=1;
        }

        //1.创建pb对象
        PageBean<User> pb=new PageBean<User>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //3.调用 dao查询总数
        int totalCount=dao.findTotalCount();

        pb.setTotalCount(totalCount);

        //4.调用dao查询list集合
        int start=(currentPage-1)*rows;
        List<User> list = dao.findByPage(start, rows);
        pb.setList(list);
        //5.计算总页码
        int totalPage=(totalCount % rows) == 0 ? (totalCount/rows):(totalCount/rows)+1;


        pb.setTotalPage(totalPage);

        return pb;
    }
}
