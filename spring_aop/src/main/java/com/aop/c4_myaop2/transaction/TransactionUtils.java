package c4_myaop2.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c4_myaop2.transaction
 * @date 12 18:${MIMUTE}
 * @modified
 * 编程事务（需要手动 begin、commit、rollback）
 */
@Component
@Scope("prototype") //每个事务都是新的实例，目的是解决线程安全问题
public class TransactionUtils {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //全局变量：接收事务状态
    private TransactionStatus transactionStatus;

    public TransactionStatus begin(){
        System.out.println("开启事务");
        return  transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    public void commit(){
        System.out.println("提交事务");
        dataSourceTransactionManager.commit(transactionStatus);
    }

    public void rollback(){
        System.out.println("回滚事务");
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}
