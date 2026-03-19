package server.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WhileServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("클라이언트에 연결 요청을 기다립니다...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("===========서버 실행===========");

            // 소켓과 연결된 스트림
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // 키보드와 연결할 스트림
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while ((line = reader.readLine()) != null) {
                // equalsIgnoreCase 대소문자 구분없이 값 확인
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }

                System.out.println("클라이언트 > " + line);

                // 서버 측 컴퓨터의 키보드에서 값을 받아서 클라이언트 측으로 전송
                // ** 서버에서 응답을 받아야만 메세지를 보낼 수 있음 **
                System.out.print("서버 입력 > ");
                String serverMsg = keyboardReader.readLine(); // 블로킹 상태 (콘솔창에 글을 입력해야 함)
                // 클라이언트와 연결된 소켓 출력 스트림을 활용해서 내용을 전달한다.
                writer.println(serverMsg);

                // 서버 측에서도 더 이상 글 입력 받기 싫다면 exit 입력으로 while문 종료 처리
                if ("exit".equalsIgnoreCase(serverMsg)) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } // end of main
} // end of class
