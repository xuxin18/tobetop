package com.aop.c2_aop;

import com.aop.c2_aop.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.c2_aop
 * @date 11 10:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.add();
    }
}
