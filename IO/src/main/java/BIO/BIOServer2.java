package BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author xuxin
 * @version v1.0
 * @project study
 * @package BIO
 * @date 21 14:${MIMUTE}
 * @modified
 *
 * 多线程版，频繁创建销毁线程 低效
 */
public class BIOServer2 {

    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) {
        int port = 9010;

        try (ServerSocket ss = new ServerSocket(port)) {
            while (true) {
                Socket s = ss.accept();

                //新开一个线程去处理这个连接
                new Thread(new SocketProcess(s)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class SocketProcess implements Runnable{
        Socket s;

        public SocketProcess(Socket s){
            super();
            this.s = s;
        }
        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(s.getInputStream(), charset)
            )){
                //接收数据
                String message = null;
                while ((message = reader.readLine()) != null){
                    System.out.println(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
