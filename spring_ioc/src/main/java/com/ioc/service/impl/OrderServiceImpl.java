package com.ioc.service.impl;

import com.ioc.anno.MyService;
import com.ioc.service.OrderService;

@MyService
public class OrderServiceImpl implements OrderService {

	@Override
    public void addOrder() {
		System.out.println("添加命令");
	}

}