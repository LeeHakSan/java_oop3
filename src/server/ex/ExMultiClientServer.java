package server.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ExMultiClientServer {

    private static int PORT = 5000;
    // 접속자를 PrintWriter와 함께 벡터에 담는 용도
    private static Vector<PrintWriter> clientWriterList = new Vector<>();

    public static void main(String[] args) {
        while (true) { // 클라이언트가 접속할 때 마다 서버소켓과 클라이언트 소켓 연결을 위한 용도로 while() 사용
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {

                Socket clientSocket = serverSocket.accept(); // 블로킹


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



    }



}
