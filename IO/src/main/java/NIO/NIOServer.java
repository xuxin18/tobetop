package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuxin
 * @version v1.0
 * @project study
 * @package NIO
 * @date 21 19:${MIMUTE}
 * @modified
 */
public class NIOServer {

    private static Charset charset = Charset.forName("UTF-8");
    private static CharsetDecoder decoder = charset.newDecoder();

    public static void main(String[] args) throws IOException {
        //创建一个selector选择器（通道管理器）
        Selector selector = Selector.open();

        //获取ServerSocket通道（ServerSocketChannel类似BIO中的ServerSocket），它可以监听新连接进来的channel
        ServerSocketChannel ssc = ServerSocketChannel.open();

        int port = 9200;
        //让ServerSocketChannel监听指定的 ip端口
        ssc.bind(new InetSocketAddress(port));

        //将ServerSocketChannel设置为非阻塞模式
        ssc.configureBlocking(false);

        //将 ServerSocketChannel注册到 selector，监听状态为 OP_ACCEPT（连接到selector）的channel
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        //声明一个变量来统计通道连接的数量
        int connectCount = 0;
        //极少量线程
        int threads = 3;
        ExecutorService tpool = Executors.newFixedThreadPool(threads);

        //轮循监听 selector上是否有需要处理的事件（channel状态为 OP_ACCEPT）
        while (true){
            //如果有状态为 OP_ACCEPT的channel，则返回channel的数量
            // （注意：假设有两个channel，一个channel正在处理数据，一个channel的状态刚刚转换为OP_ACCEPT待处理，则返回的是2，而不是1
            //如果没有 状态为 OP_ACCEPT 的channel，则阻塞
            int readyChannelsCount = selector.select();

            //由于 select（）阻塞可以被中断，当他被中断则让它重新执行 select（）
            if (readyChannelsCount == 0){
                continue;
            }

            //获得所有channel的 key
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            //通过迭代器来对channel进行处理
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()){
                    //a connection was accepted by a ServerSocketChannel
                    ServerSocketChannel sssc = (ServerSocketChannel) key.channel();
                    //获得和客户端连接的通道
                    SocketChannel sc = sssc.accept();
                    //将通道设置为非阻塞
                    sc.configureBlocking(false);

                   /*
                    可以这样给服务端发送信息
                    sc.write(ByteBuffer.wrap(new String("hello,server").getBytes()));
                    */
                    //将SocketChannel向Selector注册，并让Selector检测状态为OP_READ的channel
                    sc.register(selector,SelectionKey.OP_READ,++connectCount);//顺便将连接数+1，这个可有可无
                }else if(key.isConnectable()){
                    // a connection was established with a remote server
                }else if (key.isReadable()){
                    // a channel is ready for reading
                    //交给线程池去处理
                    tpool.execute(new SocketProcess(key));

                    //将key对应的channel从selector中取消注册，防止线程池处理不及时，重复选择
                    key.cancel();
                }else if (key.isWritable()){
                    // a channel is ready for writing
                }

                //当 一个 channel处理后，将它移出
                keyIterator.remove();
            }

        }
    }

    static class SocketProcess implements Runnable{
        SelectionKey key;

        public SocketProcess(SelectionKey key) {
            this.key = key;
        }

        @Override
        public void run() {
            try {
                // key.attachment() 获取上面86行传进来的附加数据，即 connectCount
                System.out.println("连接" + key.attachment()+"发来了：" + readFromChannel() );
                //如果连接不需要了，就关闭
                key.channel().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String readFromChannel() throws IOException {
           SocketChannel sc = (SocketChannel) key.channel();

           int bfsize = 1024;
           ByteBuffer bf = ByteBuffer.allocate(bfsize);

           // 定义一个更大的buffer
            ByteBuffer bigbuf = null;

            //读的次数计数
            int count = 0;
            while ((sc.read(bf)) != -1){
                count++;

                ByteBuffer temp = ByteBuffer.allocate(bfsize*(count+1));

                if (bigbuf != null){
                    //将buffer转为读模式
                    bigbuf.flip();

                    temp.put(bigbuf);
                }
                bigbuf = temp;

                //将这次读到的数据放入大的buffer中
                bf.flip();
                bigbuf.put(bf);

                //为下次读，清理buffer
                bf.clear();
            }

            if (bigbuf != null){
                //将bigbuf翻转为（被）读模式
                bigbuf.flip();
                //将字节转为字符，返回
                return decoder.decode(bigbuf).toString();
            }
            return null;

        }
    }
}
