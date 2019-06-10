package mapper;

import orm.annotation.ExtInsert;
import orm.annotation.ExtParam;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package mapper
 * @date 24 15:${MIMUTE}
 * @modified
 */
public interface UserMapper {

    @ExtInsert("insert into t_users(name,age) values(#{userName},#{age})")
    public int insertUser(@ExtParam("userName") String userName, @ExtParam("age") int age);
}
