package orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package orm.annotation
 * @date 24 15:${MIMUTE}
 * @modified
 *
 * 自定义 插入 注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtInsert {
    String value();
}
