package c2_aop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_aop.dao
 * @date 11 15:${MIMUTE}
 * @modified
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(String name, Integer age) {
        String sql = "INSERT INTO t_users(NAME, age) VALUES(?,?);";
        int updateResult = jdbcTemplate.update(sql, name, age);
        System.out.println("updateResult:" + updateResult);
    }
}
