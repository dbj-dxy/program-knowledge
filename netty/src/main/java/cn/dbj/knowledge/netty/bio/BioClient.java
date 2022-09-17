package cn.dbj.knowledge.netty.bio;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class BioClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 6666));
        while (true) {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String input = scanner.next();
                if ("exit".equals(input)) {
                    return;
                }
                socket.getOutputStream().write(scanner.next().getBytes());
            }
        }
    }
}
