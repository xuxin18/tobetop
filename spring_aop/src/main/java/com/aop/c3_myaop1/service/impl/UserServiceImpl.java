package com.aop.c3_myaop1.service.impl;

import com.aop.c3_myaop1.service.UserService;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.c3_myaop1.service.impl
 * @date 12 15:${MIMUTE}
 * @modified
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("添加对象");
    }

    @Override
    public void updateUser() {
        System.out.println("更新对象");
    }

    @Override
    public void deleteUser() {
        System.out.println("删除对象");
    }
}
