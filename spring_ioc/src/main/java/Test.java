import com.ioc.service.UserService;
import com.ioc.spring.MyClassPathXmlApplicationContext;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package PACKAGE_NAME
 * @date 16 14:${MIMUTE}
 * @modified
 * 注解版 IOC
 */
public class Test {

    public static void main(String[] args) throws Exception {
        MyClassPathXmlApplicationContext app = new MyClassPathXmlApplicationContext("com.ioc.service");
        UserService userService = (UserService) app.getBean("userServiceImpl");
        System.out.println(userService);
        userService.add();
    }
}
