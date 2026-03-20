package server.ex;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ICloud 처럼
 * 서버측에서 파일을 받는 역할
 * 클라이언트는 서버와 연결해서 파일을 전송함
 * 추후에 서버에 있는 파일들을 클라이언트에서 다운 받을 수 있도록 할 것
 */
public class FileServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedInputStream input;
    private BufferedOutputStream output;

    public final void run() {
        try {
            // 1. 소켓 생성, 연결
            setUpSocket();
            // 2. 스트림 연결
            setUpStream();
            // 3. 파일 복사
            copyFile();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 소켓, 스트림 닫기
            // 스트림을 닫지 않으면 파일이 제대로 전송되지 않을 수 있음
            closeAll();
        }
    }

    public void setUpSocket() throws IOException {
        serverSocket = new ServerSocket(5000);
        clientSocket = serverSocket.accept();
        System.out.println("Connected to the Client");
    }

    public void setUpStream() throws IOException {
        output = new BufferedOutputStream(new FileOutputStream("recieve.txt"));
        input = new BufferedInputStream(clientSocket.getInputStream());
    }

    public void copyFile() throws IOException {
        int data;
        byte[] bytes = new byte[1024];
        while ((data = input.read(bytes)) != -1) {
            output.write(bytes, 0, data);
        }
    }

    public void closeAll() {
        try {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        new FileServer().run();
    }


//    public static void main(String[] args) {
//        try (ServerSocket serverSocket = new ServerSocket(5000);
//             Socket clientSocket = serverSocket.accept();
//             BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());
//             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("received.txt"))){
//
//            int data;
//            byte [] bytes = new byte[1024];
//            while ((data = in.read(bytes)) != -1) {
//                out.write(bytes, 0, data);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
