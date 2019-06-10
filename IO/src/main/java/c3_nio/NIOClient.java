package c3_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author xuxin
 * @version v1.0
 * @project study
 * @package NIO
 * @date 22 09:${MIMUTE}
 * @modified
 */
public class NIOClient {

    static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) {
        try (SocketChannel sc = SocketChannel.open()){
            //连接 会阻塞
            boolean connected = sc.connect(new InetSocketAddress("localhost", 9200));
            System.out.println("connected=" + connected);

            //写数据
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入： ");
            String message = scanner.nextLine();
            ByteBuffer bf = ByteBuffer.wrap(message.getBytes(charset));

            while (bf.hasRemaining()){
                int writeCount = sc.write(bf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
