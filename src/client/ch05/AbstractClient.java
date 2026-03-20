package client.ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class AbstractClient {

    private String name;
    private Socket socket;
    private PrintWriter writerStream;
    private BufferedReader readerStream;
    private BufferedReader keyboardReader;

    public AbstractClient(String name) {
        this.name = name;
    }

    // 실행 흐름 정의 (자식클래스에서 재정의 불가)
    public final void run() {
        try {
            connectToServer();
            setupStreams();
            startCommunication();
        } catch (Exception e) {
            e.printStackTrace(); // 스택 구조로 예외를 추적하게 하는 부분을 출력
        } finally {
            // 최종에는 socket.close() 처리
            cleanUp();
        }
    }

    // AbstractClient를 상속 받은 자식 객체는 connectToServer() 메서드를 재정의 해야함
    protected abstract void connectToServer() throws IOException;

    private void setupStreams() throws IOException {
        writerStream = new PrintWriter(socket.getOutputStream(), true);
        readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        keyboardReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void startCommunication() throws InterruptedException {
        Thread readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String msg;
                try {
                    while ((msg = readerStream.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (Exception e) {
                    System.err.println("서버와 연결이 끊겼습니다.");
                }

            }
        });

        // 키보드에서 값을 받아서 소켓으로 서버에 메세지 전송
        Thread writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String input;
                    while ((input = keyboardReader.readLine()) != null) {
                        writerStream.println("[" + name + "] " + input);
                    }
                } catch (Exception e) {
                    System.err.println("전송 중 오류 발생");
                }
            }
        });

        readThread.start();
        writeThread.start();

        readThread.join();
        writeThread.join();

    } // end of startCommunication()

    // 외부에서 메서드를 통해 주소값을 주입받음
    protected void setSocket(Socket socket) {
        this.socket = socket;
    }

    private void cleanUp() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
