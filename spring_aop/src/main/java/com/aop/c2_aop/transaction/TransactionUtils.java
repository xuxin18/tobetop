package c2_aop.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c2_aop.transaction
 * @date 11 15:${MIMUTE}
 * @modified
 *
 * 编程事务（需要手动 begin、commit、rollback）
 */
//注入到 spring容器中
@Component
@Scope("prototype")  //todo 据说 设置成原型模式可以解决线程安全问题
public class TransactionUtils {

    //获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus begin(){
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transaction;
    }

    //提交事务
    public void commit(TransactionStatus transaction){
        dataSourceTransactionManager.commit(transaction);
    }

    //回滚事务
    public void rollback(TransactionStatus transaction){
        dataSourceTransactionManager.rollback(transaction);
    }
}
