package com.aop.c4_myaop2;

import com.aop.c4_myaop2.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.c4_myaop2
 * @date 15 11:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.add();
    }
}
