package io.ch15_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class TypingRecord2 {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int count = 0;
        System.out.println("===타자 연습 기록기===");
        System.out.println("1. 문장 저장");
        System.out.println("2. 기록 보기");
        System.out.print("선택 : ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            saveRecord(sc);
        } else if (choice.equals("2")) {
            printRecord();
        }

        sc.close(); // 메모리 누수 방지

    } // end of main

    private static void printRecord() throws Exception {
        System.out.println("\n===저장된 기록 ===");
        try (FileInputStream fin = new FileInputStream("typing_record.txt")) {
            int data;
            int lineNumber = 1; // 현재 출력 중인 줄 번호
            StringBuilder sb = new StringBuilder();
            // StringBuilder: 문자를 하나씩 이어붙이는 가변 문자열 버퍼
            // String += "가" 를 반복하면 매번 새로은 객체가 생겨 느리므로 StringBuilder 사용한다.
            while ((data = fin.read()) != -1) {
                System.out.print((char) data);
                // 출력할 때 만약 \n(개행문자) 이 들어온다면 카운트를 1씩 올리겠다
                if((char) data == '\n') {
                    // 개행 문자(\n) 만났다 == 한 줄이 끝났다.
                    lineNumber++;
                } else {
                    // 개행문자(\n) 아니라면 sb에 계속 이어붙임
                    sb.append((char) data);
                }
            } // end of while
//            System.out.println( "\n" + sb.toString());
            System.out.println("\n총 " + lineNumber + "개의 기록이 있습니다");
        }
    }

    private static void saveRecord(Scanner sc) {
        System.out.print("연습한 문장을 입력하세요 : ");
        String input = sc.nextLine();

        try (FileOutputStream fos = new FileOutputStream("typing_record.txt", true)) {
            fos.write(input.getBytes());
            // 줄바꿈 추가
            fos.write("\n".getBytes());
            System.out.println("저장 완료!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
