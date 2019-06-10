import connection.ConnectionPoolManager;

import java.sql.Connection;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package PACKAGE_NAME
 * @date 23 10:${MIMUTE}
 * @modified
 *
 * 核心参数：
 *      空闲线程（没有被使用的连接）
 *      活动线程（正在被使用的连接）
 * 数据库连接池实现原理：
 *      1.初始化线程池（初始化空闲线程）
 *      2.调用 getConnection 方法 ，获取连接
 *              先用 freeConnection 获取当前连接，存放在 activeConnection
 *      3.调用 releaseConnection 方法， 释放连接，资源回收
 *              获取到 activeConnection集合连接，转移到 freeConnection集合中
 *
 *
 *
 */
public class Test {

    public static void main(String[] args) {
        ThreadConn tc = new ThreadConn();
        for (int i=0; i<3; i++){
            Thread t = new Thread(tc,"线程i:" + i);
            t.start();
        }

    }
}

class ThreadConn implements Runnable{
    @Override
    public void run() {
        for (int i=0; i<10; i++){
            Connection connection = ConnectionPoolManager.getConnection();
            System.out.println(Thread.currentThread().getName() + ",connection" + connection);
            ConnectionPoolManager.releaseConnection(connection);
        }
    }
}