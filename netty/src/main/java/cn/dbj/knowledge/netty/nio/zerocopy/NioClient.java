package cn.dbj.knowledge.netty.nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NioClient {

    public static void main(String[] args) throws Exception {
        try (SocketChannel socketChannel = SocketChannel.open();
             FileInputStream fileInputStream = new FileInputStream("D:\\Projects\\program-knowledge\\netty\\file.txt")) {
            socketChannel.connect(new InetSocketAddress("localhost", 7001));
            // 得到一个文件channel
            FileChannel fileChannel = fileInputStream.getChannel();
            // 在linux下一个transferTo 方法就可以完成传输
            // 在windows 下 一次调用 transferTo 只能发送8m , 就需要分段传输文件, 而且要阻塞
            // transferTo 底层使用到零拷贝
            long startTime = System.currentTimeMillis();
            long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
            System.out.println("发送的总的字节数 = " + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));
            fileChannel.close();
        }
    }
}
