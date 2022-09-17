package cn.dbj.knowledge.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author DBJ
 * @date 2020-07-28
 */
public class NioServer {

    public static void main(String[] args) throws Exception{
        // 创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 获取Selector对象
        Selector selector = Selector.open();
        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 注册serverSocketChannel 到selector, 事件为 OPS_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 循环等待客户端连接
        while(true) {
            // 没有事件发生
            if (selector.select(1000) == 0) {
                System.out.println("服务端等待了1秒, 无连接");
                continue;
            }
            // 如果返回>0, 就获取到相关的selectionKeys集合
            // 1.返回>0, 表示已经获取到关注的事件
            // 2.selector.selectedKeys() 返回关注事件的集合
            //   通过selectionKeys反向获取通道
            System.out.println("keys" + selector.keys().size());
            System.out.println("selectedKeys" + selector.selectedKeys().size());
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历selectionKeys, 使用迭代器
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                // 获取SelectionKey
                SelectionKey key = keyIterator.next();
                // 根据key 对应的通道发生的事件做相应处理
                // 有新的客户端连接
                if (key.isAcceptable()) {
                    // 给该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 将socketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    // 打印连接信息
                    System.out.println("客户端: " + socketChannel.getRemoteAddress() + "连接成功");
                    // 将socketChannel 注册到Selector, 关注事件为OP_READ, 同时给socketChannel 关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(256));
                }
                // 发生OPS_READ
                if (key.isReadable()) {
                    // 通过key 反向获取到对应的channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 获取到channel 关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("客户端消息: " + new String(buffer.array()));
                }
                // 手动移除当前的selectorKey, 防止重复操作
                keyIterator.remove();
            }
        }
    }
}
