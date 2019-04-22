package c2_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_aop
 * @date 03 20:${MIMUTE}
 * @modified
 *
 * 切面类
 *
 * 被 @Aspect 注解的类 为 切面类
 *
 */

@Aspect
@Component
public class AopLog {
    /*
        @Pointcut 切点表达式：括号中代表切点
        被 @Pointcut 注解 的方法称为 point签名：主要是为了方便其他的注解引用切入点表达式
    */
    @Pointcut("execution(* c2_aop.service.UserService.add(..))")
    public void logAdd(){}

    // @Before 前置通知
    @Before("execution(* c2_aop.service.UserService.add(..) )")
    public void before(){
        System.out.println("前置通知-在方法之前通知  ");
    }

    @After("execution(* c2_aop.service.UserService.add(..) )")
    public void after(){
        System.out.println("后置通知-在方法之前通知  ");
    }

    /*
        环绕通知：在方法 之前 和 之后处理事情
            需要传入 ProceedingJoinPoint
    */
    //@Around("logAdd()")
    @Around("execution(* c2_aop.service.UserService.add(..) )")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知-调用切点方法之前执行  ");

        //注意：如果在调用目标方法 产生异常时， 异常被抛出时后面的方法不会继续执行，异常被 try-catch时，后面的方法会执行。建议使用抛出异常的方式
        proceedingJoinPoint.proceed();//调用目标方法（即切点）

        System.out.println("环绕通知-调用切点方法之后执行  ");
    }


    @AfterThrowing("execution(* c2_aop.service.UserService.add(..) )")
    public void afterThrowing(){
        System.out.println("异常通知  ");
    }

    /*
        可以获取 切点方法的返回值
    */
    @AfterReturning("execution(* c2_aop.service.UserService.add(..) )")
    public void afterReturning(){
        System.out.println(" 返回值。。。 ");
    }


}
