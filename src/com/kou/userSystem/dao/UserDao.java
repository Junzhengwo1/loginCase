package com.kou.userSystem.dao;

import com.kou.userSystem.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author dell
 *
 * 用户操作的dao
 */
public interface UserDao {

    /**
     *
     * @return
     */
    public abstract List<User> findAll();

    User findUserByUsernameAndPassword(String username, String password);

    void add(User user);

    /**
     * 根据id删除user
     * @param id
     */
    void delete(int id);

    User findById(int i);

    void update(User user);

    /**
     * 查询总记录数
     * @return
     * @param
     */
    int findTotalCount();

    /**
     * 分页查询每页记录
     * @param start
     * @param rows

     * @return
     */
    List<User> findByPage(int start, int rows);
}
