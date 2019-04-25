package connection;

import connection.pool.ConnectionPoolImpl;

import java.sql.Connection;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package connection
 * @date 23 16:${MIMUTE}
 * @modified
 *
 * 管理线程池
 */
public class ConnectionManager {
    public static DbBean dbBean = new DbBean();
    public static ConnectionPoolImpl connectionPool = new ConnectionPoolImpl(dbBean);

    //获取连接（重复利用机制）
    public static Connection getConnection(){
        return connectionPool.getConnection();
    }

    //释放连接（可回收机制）
    public static void releaseConnection(Connection c){
        connectionPool.releaseConnection(c);
    }
}
