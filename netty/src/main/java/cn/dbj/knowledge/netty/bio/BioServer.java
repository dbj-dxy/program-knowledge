package cn.dbj.knowledge.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author DBJ
 * @date 2020-07-14
 */
public class BioServer {


    public static void main(String[] args) throws Exception {

        ExecutorService service = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动");

        while (!Thread.currentThread().isInterrupted()) {
            final Socket accept = serverSocket.accept();
            System.out.println("客户端：" + accept.getRemoteSocketAddress() + "已经上线");
            service.execute(() -> handler(accept));
            printAllThread();
        }

    }

    public static void handler(Socket socket) {
        System.out.println("当前线程组=" + Thread.currentThread().getThreadGroup() + ", 当前线程=" + Thread.currentThread());
        try {
            byte[] bytes = new byte[5];
            InputStream inputStream = socket.getInputStream();

            while (true) {
                int read = inputStream.read(bytes);

                if (read == -1) {
                    break;
                }
                System.out.print("读取字节长度=" + read);
                System.out.println(", 接收消息=" + new String(bytes, 0, read));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("客户端：" + socket.getRemoteSocketAddress() + "已经下线");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void printAllThread() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
        while (null != group) {
            topGroup = group;
            group = group.getParent();
        }
        if (null == topGroup) {
            return;
        }
        // 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
        // 获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slackList);
        // copy into a list that is the exact size
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
        System.out.println("Thread list size == " + list.length);
        for (Thread thread : list) {
            System.out.println(thread.getName());
        }
    }
}
