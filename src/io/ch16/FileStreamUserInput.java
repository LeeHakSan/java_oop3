package io.ch16;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileStreamUserInput {
    public static void main(String[] args) {
        writeUserInputToFile("user_input.txt");
        readFromFile("user_input.txt");

    }

    public static void writeUserInputToFile(String fileName) {
        /**
         * 키보드 입력은 --> InputStreamReader(System.in) (바이트 -> 문자)
         * 파일에 쓰기 --> FileWriter(fileName)           (문자기반 파일 출력)
         */

        try (InputStreamReader reader = new InputStreamReader(System.in);
             FileWriter writer = new FileWriter(fileName, true)) {
            System.out.println("텍스트를 입력하세요 (종료: ctrl + d)");
            // 1. 사용자가 입력한 값을 받자 - 키보드
            int charCode;
            while ((charCode = reader.read()) != -1) {
                writer.write((char) charCode);
                // 문자 하나 받을 때 마다 즉시 파일 저장
                writer.flush();
            }
            System.out.println(fileName + "텍스트를 모두 작성함");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    // 파일에서 텍스트를 읽는 메서드를 직접 구현해보세요
    public static void readFromFile(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            int charCode;
            while ((charCode = reader.read()) != -1) {
                System.out.print((char)charCode);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
