package client.ex;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {
    private Socket socket;
    private BufferedOutputStream output;
    private BufferedInputStream input;

    private final void run() {
        try {
            // 1. 소켓 연결
            setUpSocket();
            // 2. 스트림 생성
            setUpStream();
            // 3. 파일 복사
            copyFile();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 4. 소켓, 스트림 닫기
            // 스트림을 닫지 않으면 파일이 제대로 전송되지 않을 수 있음
            closeAll();
        }

    }

    public void setUpSocket() throws IOException {
        socket = new Socket("localhost", 5000);
        System.out.println("Connected to the server");
    }

    public void setUpStream() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("복사할 파일을 입력하세요 : ");
        String fileName = sc.nextLine();
        output = new BufferedOutputStream(socket.getOutputStream());
        input = new BufferedInputStream(new FileInputStream(fileName));
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
            if (output != null) {
                output.close();
            }
            if (input != null) {
                input.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("보낼 파일을 입력하세요 : ");
//        String fileName = sc.nextLine();
//        try (Socket socket = new Socket("localhost", 5000);
//             BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
//             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
//
//            System.out.println("서버와 연결 됐습니다");
//            int data;
//            byte[] bytes = new byte[1024];
//            while ((data = bis.read(bytes)) != -1) {
//                bos.write(bytes, 0, data);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    public static void main(String[] args) {
        new FileClient().run();
    }

}
