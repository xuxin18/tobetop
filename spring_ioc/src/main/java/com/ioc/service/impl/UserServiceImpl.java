package com.ioc.service.impl;

import com.ioc.anno.MyResource;
import com.ioc.anno.MyService;
import com.ioc.service.OrderService;
import com.ioc.service.UserService;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.ioc.service.impl
 * @date 16 15:${MIMUTE}
 * @modified
 */

@MyService   //将该类注入到 spring容器中
public class UserServiceImpl implements UserService {
    public UserServiceImpl(){
        System.out.println("通过反射初始化spring容器中的bean");
    }

    @MyResource
    private OrderService orderServiceImpl;

    @Override
    public void add() {
        orderServiceImpl.addOrder();
    }
}
