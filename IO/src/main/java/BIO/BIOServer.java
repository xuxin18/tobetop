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
 * @date 21 10:${MIMUTE}
 * @modified
 * 普通版 阻塞   一个线程执行完了，另一个线程再执行
 */
public class BIOServer {

    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) {
        int port = 9010;

        try (ServerSocket ss = new ServerSocket(port)){
            while (true){
                try {
                    //接收连接
                    Socket s = ss.accept();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream(),charset));

                    String message = null;

                    //接收数据
                    while ((message = reader.readLine()) != null){
                        System.out.println(message);
                    }
                    s.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
