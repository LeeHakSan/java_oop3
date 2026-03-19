package io.ch18;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        // 파일 경로 지정
        String sourceFilepath = "employees.zip";
        String destinationFilepath = "employees_copy.zip";

        // 소요시간 측정 시작
        // 현재 시각을 나노초(10억분의 1초) 단위로 변환
        long startTime = System.nanoTime();

        // 파일 복사 기능

        try (FileInputStream fis = new FileInputStream(sourceFilepath);
        FileOutputStream fos = new FileOutputStream(destinationFilepath)) {
            // employees.zip 에서 1 바이트씩 읽어서 employees_copy.zip으로 1바이트씩 쓰기
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
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
