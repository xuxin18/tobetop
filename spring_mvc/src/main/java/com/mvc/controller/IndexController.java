package com.mvc.controller;

import com.mvc.annotation.MyController;
import com.mvc.annotation.MyRequestMapping;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.mvc.controller
 * @date 22 14:${MIMUTE}
 * @modified
 */

@MyController
@MyRequestMapping("/ext")
public class IndexController {

    @MyRequestMapping("/test")
    public String test(){
        System.out.println("手写springmvc");
        return "index";
    }
}
