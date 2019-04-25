package BIO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author xuxin
 * @version v1.0
 * @project study
 * @package BIO
 * @date 21 10:${MIMUTE}
 * @modified
 */
public class BIOClient implements Runnable {

    private String host;

    private int port;

    private Charset charset = Charset.forName("UTF-8");

    public BIOClient(String host, int port) {
        super();
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try (Socket s = new Socket(host, port); OutputStream out = s.getOutputStream()){
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入： ");
            String message = scanner.nextLine();
            out.write(message.getBytes(charset));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BIOClient client = new BIOClient("localhost", 9200);//9010
        client.run();
    }
}
