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
 * 自定义参数注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ExtParam {
    String value();
}
