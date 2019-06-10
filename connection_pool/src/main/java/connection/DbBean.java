package connection;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package connection
 * @date 23 14:${MIMUTE}
 * @modified
 *
 * 外部配置文件信息
 */
public class DbBean {

    private String driverName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://10.168.1.200:3306/renren_fast";
    private String userName = "admin";
    private String password = "admin@2017!@#";

    private String poolName = "thread01";//连接池名字
    private int minConnection = 1;//空闲池，最小连接数
    private int maxConnection = 10;//空闲池，最大连接数
    private int initConnection = 5;//初始化连接数
    private long connTimeOut = 1000;//重复获得连接的频率
    private int maxActiveConnections = 100;//最大允许的连接数（和数据库对应）
    private long connectionTimeOut = 1000*60*20;//连接超时时间，默认为20分钟

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public int getMinConnection() {
        return minConnection;
    }

    public void setMinConnection(int minConnection) {
        this.minConnection = minConnection;
    }

    public int getMaxConnection() {
        return maxConnection;
    }

    public void setMaxConnection(int maxConnection) {
        this.maxConnection = maxConnection;
    }

    public int getInitConnection() {
        return initConnection;
    }

    public void setInitConnection(int initConnection) {
        this.initConnection = initConnection;
    }

    public long getConnTimeOut() {
        return connTimeOut;
    }

    public void setConnTimeOut(long connTimeOut) {
        this.connTimeOut = connTimeOut;
    }

    public int getMaxActiveConnections() {
        return maxActiveConnections;
    }

    public void setMaxActiveConnections(int maxActiveConnections) {
        this.maxActiveConnections = maxActiveConnections;
    }

    public long getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(long connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }
}
