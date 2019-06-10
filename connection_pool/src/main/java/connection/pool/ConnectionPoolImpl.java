package connection.pool;

import connection.DbBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package connection.pool
 * @date 23 14:${MIMUTE}
 * @modified
 */
public class ConnectionPoolImpl implements  ConnectionPool {

    //使用线程安全的集合 空闲线程容器（存放没有被使用的连接）
    private List<Connection> freeConnection = new Vector<Connection>();

    //使用线程安全的集合 活动线程容器（存放正在使用的连接）
    private List<Connection> activeConnection = new Vector<Connection>();

    private DbBean dbBean;

    //线程安全 count 为 连接数
    private AtomicInteger countConn = new AtomicInteger(0);

    public ConnectionPoolImpl(DbBean dbBean) {
        //获取配置文件
        this.dbBean = dbBean;
        init();
    }

    //初始化线程池（初始化空闲线程）
    private void init(){
        if (dbBean == null){
            try {
                throw new Exception("未找到位置文件");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //1.获取初始化连接数
        for (int i = 0; i<dbBean.getInitConnection(); i++){
            //2.创建 Connection 连接
            Connection c = newConnection();
            if (c != null){
                //3.存放到freeConnection
                freeConnection.add(c);
            }
        }


    }

    @Override
    public synchronized Connection getConnection() {
        Connection c = null;

        //当前创建的连接数 < 最大连接数
        if (countConn.get() < dbBean.getMaxActiveConnections()){
            //判断空闲线程是否有数据
            if (freeConnection.size() > 0){
                //空闲线程（连接）存在连接,则从空闲线程中个连接
                c = freeConnection.remove(0);
            }else {
                //创建新的连接
                c = newConnection();
            }

            //判断连接是否可用
            if (isAvailable(c)){
                //向活动线程中 存
                activeConnection.add(c);
            }else {
                //获取连接失败，则重试
                countConn.getAndDecrement();
                c = getConnection();
            }

        }else {
            //大于活动连接数，则进行等待
            try {
                wait(dbBean.getConnTimeOut());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
            //重试
            getConnection();

        }
        return c;
    }

    @Override
    public synchronized void releaseConnection(Connection c) {
        try {
            //判断连接是否可用
            if (isAvailable(c)){
                //判断空闲线程是否已满
                if (freeConnection.size() < dbBean.getMaxConnection()){
                    freeConnection.add(c);
                }else {
                    //空闲线程已满
                    c.close();
                }

                activeConnection.remove(c);
                countConn.getAndDecrement();
                notifyAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Connection newConnection(){
        try {
            Class.forName(dbBean.getDriverName());
            Connection connection = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUserName(), dbBean.getPassword());
            countConn.incrementAndGet();
            return  connection;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isAvailable(Connection c){
        try {
            if (c == null || c.isClosed()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
