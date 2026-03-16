package io.ch15_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class TypingRecord {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== 타자 연습 기록기 ======");
        System.out.println("1. 문장 저장");
        System.out.println("2. 기록 보기");
        System.out.print("선택 : ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            saveRecord(sc);
        } else if (choice.equals("2")) {
            printRecord();

        }

        sc.close();

    }// end of main

    private static void printRecord() {
        System.out.println("\n===== 저장된 기록 =====");
        try (FileInputStream fis = new FileInputStream("typing_record.txt")) {
            int data;
            // 파일 읽을 때 제일 마지막은 -1이 되기 때문에 FileInputStream의 read() 메서드가 파일 내부의 글을 다 읽고 나면
            // read() 메서드에 -1 이 들어옴
            while ((data = fis.read()) != -1) {
                System.out.print((char) data);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void saveRecord(Scanner sc) {
        System.out.print("연습한 문장을 입력하세요 : ");
        String input = sc.nextLine();

        try (FileOutputStream fos = new FileOutputStream("typing_record.txt", true)) {
            fos.write(input.getBytes());
            // 줄바꿈 추가 -> 일반 문자열에 .getBytes() 호출 가능
            fos.write("\n".getBytes());
            System.out.println("저장 완료!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
