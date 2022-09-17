package cn.dbj.knowledge.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author DBJ
 * @date 2020-07-28
 */
public class NioClient {
    public static void main(String[] args) throws Exception {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 配置服务端ip 和 port
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);
        // 连接服务端
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接需要时间, 客户端不会阻塞");
                doOtherThing();
            }
        }
        // 如果连接成功, 发送数据
        String msg = "hello, server";
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(buffer);
        System.in.read();
    }
    public static void doOtherThing() {
        
    }
}
