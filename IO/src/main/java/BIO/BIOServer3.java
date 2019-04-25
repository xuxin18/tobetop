package BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuxin
 * @version v1.0
 * @project study
 * @package BIO
 * @date 21 15:${MIMUTE}
 * @modified
 * 线程池版
 * 当并发量非常大时，将导致没有线程处理请求，请求响应时间长，甚至拒绝服务
 */
public class BIOServer3 {

    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) {
        int port = 9010;
        int threads = 100;
        //创建一个定长线程池，线程池容量固定为100，线程超过100则在队列中等待
        ExecutorService tpool = Executors.newFixedThreadPool(threads);

        try (ServerSocket ss = new ServerSocket(port)){
            while(true){
                Socket s = ss.accept();
                //将创建的线程放入连接池中惊醒执行
                tpool.execute(new SocketProcess(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //执行玩所有线程后关闭线程池
        tpool.shutdown();
    }

    static class SocketProcess implements Runnable{
        Socket s;

        public SocketProcess(Socket s) {
            super();
            this.s = s;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(s.getInputStream(), charset)
            )){
                String message = null;
                //接收数据
                while ((message = reader.readLine()) != null){
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
