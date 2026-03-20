package client.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("채팅 이름을 입력하세요 : ");
        String name = sc.nextLine();

        // 강사 컴퓨터 IP 주소 : 192.168.4.101
        try (Socket socket = new Socket("192.168.4.101", 5000)) {

            System.out.println(name + " 님, 채팅방 입장 했음(종료 : exit)");

            // 소켓에서 연결 할 입력, 출력 스트림 2개가 필요하다.
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 클라이언트에서 키보드에서 값을 입력 받을 스트림이 필요 하다.
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            // 읽기 스레드 (서버측에서 값을 계속 받을 수 있도록 처리)
            Thread readThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String serverMessage;
                        while ( (serverMessage = reader.readLine()) != null ) {

                            System.out.println("서버 : " + serverMessage);
                        }
                    } catch (IOException e) {
                        System.out.println("서버측과 연결이 끊겼습니다.");
                    }
                }
            });

            // 쓰기 스레드 생성 (클라이언트 측 키보드에서 값을 받아서 서버측으로 전송)
            Thread writeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String clientMessage;
                        while ( (clientMessage = keyboardReader.readLine()) != null ) {
                            if("exit".equalsIgnoreCase(clientMessage)) {
                                System.out.println("채팅방 종료");
                                writer.println("[" + name +"] 님이 퇴장했습니다");
                                break;
                            }
                            // 서버에 메세지 전송
                            writer.println("[" + name + "]" + clientMessage);
                        }
                    } catch (IOException e) {
                        System.out.println("메세지 전송 중 오류가 발생했습니다.");
                    }
                }
            });

            readThread.start();
            writeThread.start();
            readThread.join();
            writeThread.join();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    } // end of mai
}
