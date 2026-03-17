package io.ch16;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FIleStreamBasic {
    public static void main(String[] args) {

        writeToFile("basic_output.txt");
        System.out.println("--------------------------");
        readFromFile("basic_output.png");

    } // end of main

    // 파일에 텍스트를 쓰는 메서드 (문자 기반 스트림 사용)
    // append를 설정 하지 않으면 기본값이 false
    // 덮어쓰기는 true
    public static void writeToFile(String fileName) {
        /**
         * FileWriter 는 문자 기반 출력 스트림
         * FileOutPutStream과 fos.write(byte[]) 달리 write(String)이 가능
         * getBytes 변환이 필요없음
         */
        try (FileWriter writer = new FileWriter(fileName)) {
            String text = "자바 문자 기반 스트림\n";
            writer.write(text);
            writer.write("추가 문자열을 기록합니다");
//            writer.flush(); 생략 가능
            System.out.println("파일에 텍스트를 잘 기록 했습니다.");
        } catch (Exception e) {
            System.err.println("파일 쓰기 중 오류 발생 : " + e.getMessage());
        }
    }

    public static void readFromFile(String fileName) {
        /**
         * FileReader는 문자 기반 입력 스트림이다
         * read() 는 한 문자씩 읽어 유니코드값(정수)로 반환 한다.
         * FileInputStream과 같지만 한글이 깨지지 않음
         */
        try (FileReader fileReader = new FileReader(fileName)) {

            int charCode;
            while ((charCode = fileReader.read()) != -1) {
                System.out.print((char) charCode);
            }

        } catch (Exception e) {
            System.out.println("해당하는 파일이 없습니다 : " + e.getMessage());
        }
    }
}
