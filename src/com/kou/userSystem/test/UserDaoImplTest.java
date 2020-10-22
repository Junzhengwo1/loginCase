package com.kou.userSystem.test;


import com.kou.userSystem.dao.UserDao;
import com.kou.userSystem.domain.User;
import com.kou.userSystem.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author JIAJUN KOU
 */
public class UserDaoImplTest {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Test
    public void testFindAll(){
        String sql="select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        for (User user : users) {
            System.out.println(user);
        }

    }
}
