package com.aop.c4_myaop2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.c4_myaop2.annotation
 * @date 12 19:${MIMUTE}
 * @modified
 * 自定义注解
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnoT {
}
