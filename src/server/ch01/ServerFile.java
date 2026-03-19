package server.ch01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {
    public static void main(String[] args) {
        // 소켓 통신을 위한 서버측 테스트 코드 1
        // 내 IP 주소는 알고 있음
        // 포트 번호를 열고 클라이언트의 연결 요청을 기다리는 소켓
        try (ServerSocket serverSocket = new ServerSocket(5001)) {

            System.out.println("서버시작");
            // 1. accept() 클라이언트가 연결할 때 까지 이 줄에서 멈춤 (블로킹)
            Socket clientSocket = serverSocket.accept(); // 코드가 아래로 안 내려감 (블로킹)
            System.out.println("클라이언트가 연결 됐습니다.");
            // 2. 소켓 객체가 생성되면 accept() 이 소켓이 클라이언트 소켓 과 연결되어 있는 소켓이다

            // I/O 단원에서 배운 체인 그대로
            InputStream input = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // 클라이언트가 보낸 한 줄을 읽음
            String message = reader.readLine();
            // 내 서버측 콘솔창에 출력
            System.out.println("클라이언트 메세지 : " + message);

            clientSocket.close();


        } catch (IOException e) {
            System.err.println("오류 발생 : 포트 5000 이미 사용중 이거나 연결에 실패했습니다");
        }


    }


}
