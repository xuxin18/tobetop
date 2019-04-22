package c2_aop;

import c2_aop.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_aop
 * @date 11 16:${MIMUTE}
 * @modified
 *
 * 切面类
 */

@Component
@Aspect
public class AopTransaction {

    @Autowired
    private TransactionUtils transactionUtils;

    @Around("execution(* c2_aop.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("开启事务");
        TransactionStatus transaction = transactionUtils.begin();

        proceedingJoinPoint.proceed();

        transactionUtils.commit(transaction);
        System.out.println("提交事务");
    }

    @AfterThrowing("execution(* c2_aop.service.UserService.add(..))")
    public void afterThrowing(){
        System.out.println("回滚");
        //获取当前程序状态 进行回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

}
