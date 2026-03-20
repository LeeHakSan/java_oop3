package server.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MultiClientServer {

    private static final int PORT = 5000;
    // 연결된 모든 클라이언트의 출력 스트림을 보관할 자료 구조
    // Vector는 멀티쓰레드 환경에서 안전하게 작동한다
    private static Vector<PrintWriter> clientWriterList = new Vector<PrintWriter>();

    public static void main(String[] args) {

        System.out.println("서버시작 ...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // 클라이언트가 접속할 때 까지 대기 하고 무한 반복
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
                // 클라이언트 접속시 전담 쓰레드 생성 --> 메인 쓰레드는 다시 대기
                System.out.println("클라이언트 접속 . 현재 접속자 " + clientWriterList.size() + "명");

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    } // end of main

    // 각 클라이언트와 통신을 담당하는 정적 내부 클래스
    private static class ClientHandler extends Thread {

        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        // 쓰레드 start() 호출되면 서브 작업자가 일 함
        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // 추후 접속자 명수 확인 또는 방송 (브로드 캐스트)를 하기 위해 벡터 자료구조에 저장할 예정
                clientWriterList.add(out);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("수신 : " + message);
                    // 받은 메세지를 연결된 모든 클라이언트에게 전송(브로드캐스트)
                    broadcast(message);

                }

            } catch (Exception e) {
                System.out.println("누군가 채팅을 종료 했습니다");
            } finally {
                // 클라이언트가 강제 종료 및 exit 요청을 했다면 서버에서 괸리하고 있는
                // 벡터 안에 나의 출력 스트림을 제거 해야 한다
                clientWriterList.remove(out); // 출력 스트림 제거
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    System.out.println("현재 클라이언트 퇴장 : " + clientWriterList.size());
                }
            }
        } // end of run

        // 방송하기
        private void broadcast(String message) {
            for (PrintWriter writer : clientWriterList) {
                writer.println(message);
            }
        }
    } // end of ClientHandler

}// end of class
















