package io.ch14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileInputStream2 {
    public static void main(String[] args) {

        // 주의 a.txt 파일에서 바이트 단위로 데이터를 읽어서 콘솔에 출력할 예정
        // 한글이 있다면 깨짐 발생

        try (FileInputStream in = new FileInputStream("a.txt")) {
            // 파일에서 더이상 읽을 데이터가 없으면 -1 을 반환함
            int readData; // 한 바이트씩 데이터를 읽을 메모리 공간 선언

            while ((readData = in.read()) != -1) {
                System.out.print((char) readData);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // try 블록이 끝나면 in.close() 메서드가 자동으로 호출 됨
        // try - with - resource 구문을 사용한다

//        try () {
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }


    } // end of main
} // end of class
