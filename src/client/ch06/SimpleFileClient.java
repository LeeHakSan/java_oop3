package client.ch06;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleFileClient {

    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("파일 전송 클라이언트 시작");

        Scanner sc = new Scanner(System.in);
        System.out.print("전송할 파일 경로를 입력하세요 (예: C:\\_work_java\\test.txt) ");
        String filePath = sc.nextLine();

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("파일이 존재하지 않습니다");
            return;
        }

        try (Socket socket = new Socket("localhost", PORT)) {
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            // 1단계 : 파일 이름 전송 (고정 100바이트)
            // C:/test.txt --> file.getName() --> test.txt
            String fileName = file.getName();
            byte[] nameBytes = fileName.getBytes();
            byte[] nameBuffer = new byte[100]; // 100 바이트 선언

            // 방어적 코드 - 파일 이름이 100바이트 이상 안되게 처리
            if (nameBytes.length > 100) {
                System.out.println("파일 이름이 너무 깁니다.(최대 100자)");
                return;
            }

            // 파일 이름 전송
            for (int i = 0; i < nameBytes.length; i++) {
                nameBuffer[i] = nameBytes[i];
            }
            // 서버측에서 딱 한번 100바이트 통으로 읽기 위한 코드로 작성 되어 있음.
            out.write(nameBuffer);
            out.flush();

            // 2단계 파일 내용 전송
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                out.flush();
            }

            System.out.println("전송 완료 : " + fileName);

            // 3단계 서버측 응답 수신
            byte[] responseBuffer = new byte[1024];
            int responseLength = in.read(responseBuffer);
            if (responseLength > 0) {
                System.out.println("서버 응답 : "
                        + new String(responseBuffer, 0, responseLength));
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    } // end of main

} // end of class