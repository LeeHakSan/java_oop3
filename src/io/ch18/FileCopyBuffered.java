package io.ch18;

import java.io.*;

public class FileCopyBuffered {
    public static void main(String[] args) {
        // 파일 경로 지정
        String sourceFilepath = "employees.zip";
        String destinationFilepath = "employees_copy.zip";

        // 소요시간 측정 시작
        // 현재 시각을 나노초(10억분의 1초) 단위로 변환
        long startTime = System.nanoTime();

        // 파일 복사 기능 - 빠른버전

        try (FileInputStream fis = new FileInputStream(sourceFilepath);
             FileOutputStream fos = new FileOutputStream(destinationFilepath);
             BufferedInputStream bfis = new BufferedInputStream(fis);
             BufferedOutputStream bfos = new BufferedOutputStream(fos)) {
            // 버퍼의 크기 직접 지정
            // 1바이트 1000개는 1kb
            byte[] bytes = new byte[1024];
            int data;
            while ((data = bfis.read(bytes)) != -1) {
                // 읽은 만큼 씀 / bytes배열의 길이에서 0부터 읽은 data의 크기만큼 복사
                // data가 읽은 만큼 쓰지 않으면 버퍼가 꽉 차지 않았을 때 공백이나 다른 값이 복사가 돼서
                // 파일이 깨질 수 있음.
                bfos.write(bytes, 0 , data);
            }
            System.out.println("파일 복사 완료");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        long endTime = System.nanoTime();
        // 소요 시간 계산
        long duration = endTime - startTime;
        double seconds = duration / 1_000_000_000.0; // nanoSec --> sec
        System.out.println("나노 초 값 : " + duration);
        System.out.println("초 값 : " + seconds);

    }
}
