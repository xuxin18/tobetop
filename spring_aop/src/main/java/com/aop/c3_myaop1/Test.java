package c3_myaop1;

import c3_myaop1.beanfactory.MyBeanFactory;
import c3_myaop1.service.UserService;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c3_myaop1
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
