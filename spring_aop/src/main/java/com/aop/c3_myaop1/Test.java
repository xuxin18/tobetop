package com.aop.c3_myaop1;

import com.aop.c3_myaop1.beanfactory.MyBeanFactory;
import com.aop.c3_myaop1.service.UserService;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.c3_myaop1
 * @date 12 15:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) {
        UserService userService = MyBeanFactory.createService();
        userService.addUser();
        userService.updateUser();
        userService.deleteUser();

    }
}
