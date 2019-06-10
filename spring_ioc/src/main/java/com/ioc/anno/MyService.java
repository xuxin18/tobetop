package com.ioc.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.ioc.anno
 * @date 16 14:${MIMUTE}
 * @modified
 *
 * 自定义的 service 注解: 将被注解的类文件注入spring的bean容器
 */
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyService {
}
