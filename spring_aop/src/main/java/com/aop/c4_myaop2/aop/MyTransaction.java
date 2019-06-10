package com.aop.c4_myaop2.aop;

import com.aop.c4_myaop2.annotation.MyAnnoT;
import com.aop.c4_myaop2.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package com.c4_myaop2.aop
 * @date 12 19:${MIMUTE}
 * @modified
 *
 * proceedingJoinPoint.getSignature(): 返回目标方法（切点）的签名
 * proceedingJoinPoint.getTarget(): 返回被织入增强处理的目标对象（即目标方法所在的实例）
 * proceedingJoinPoint.getArgs(): 以 Object数组 的形式返回目标方法的形参
 * 自定义事务注解的具体实现
 */

@Component
@Aspect
public class MyTransaction {

    @Autowired
    private TransactionUtils transactionUtils;

    //环绕通知
    @Around("execution(* com.aop.c4_myaop2.service.*.*(..) )")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //1.获取拦截到的方法上是否有注解；如果有则开启事务
        MyAnnoT myannot = getMyAnnoT(proceedingJoinPoint);
        if (myannot != null){
            transactionUtils.begin();
        }
        //2.通过代理来调用目标对象的方法
        proceedingJoinPoint.proceed();
        //3.判断方法上是否有注解；如果存在就提交事务
        if (myannot != null){
            transactionUtils.commit();
        }
    }

    //使用 异常通知 进行 回滚事务
    @AfterThrowing("execution(* com.aop.c4_myaop2.service.*.*(..))")
    public void afterThrowing(){
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();这种是 spring框架中的回滚
        transactionUtils.rollback();
    }

    /*
        获取方法上是否存在事务注解
    */
    private MyAnnoT getMyAnnoT(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        //获得目标方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //获得目标方法所在的 类文件
        Class target = proceedingJoinPoint.getTarget().getClass();
        //将 签名 强转为 MethodSignature 后，获取 方法的形参的类型
        Class[] parameterTypes = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes();
        //通过反射 获取 目标方法对象
        Method targetMethod = target.getMethod(methodName, parameterTypes);
        //通过目标方法对象 获取指定的 注解
        MyAnnoT myannot = targetMethod.getDeclaredAnnotation(MyAnnoT.class);

        return myannot;
    }

}
