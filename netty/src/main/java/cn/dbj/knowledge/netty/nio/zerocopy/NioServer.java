package cn.dbj.knowledge.netty.nio.zerocopy;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServer {

    public static void main(String[] args) throws Exception {
        // 创建ServerSocketChannel -> ServerSocket
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            // 绑定端口
            serverSocketChannel.bind(new InetSocketAddress(7001));
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();

                int readCount = 0;
                while (-1 != readCount) {
                    try {
                        readCount = socketChannel.read(byteBuffer);
                    } catch (Exception ex) {
                        break;
                    }
                    //倒带 position = 0 mark 作废
                    byteBuffer.rewind();
                }
            }
        }
    }
}
