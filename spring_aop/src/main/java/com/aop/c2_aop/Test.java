package c2_aop;

import c2_aop.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_aop
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
