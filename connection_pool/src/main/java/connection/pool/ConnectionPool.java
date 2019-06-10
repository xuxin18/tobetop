package connection.pool;

import java.sql.Connection;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package connection.pool
 * @date 23 14:${MIMUTE}
 * @modified
 * 数据库连接池
 */
public interface ConnectionPool {
    //获取连接（重复利用机制）
    public Connection getConnection();

    //释放连接（可回收机制）
    public void releaseConnection(Connection c);
}
