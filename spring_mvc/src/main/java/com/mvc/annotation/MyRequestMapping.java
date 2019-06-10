package com.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.mvc.annotation
 * @date 22 11:${MIMUTE}
 * @modified
 * 自定义 RequestMapping
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRequestMapping {
    String value() default "";
}
