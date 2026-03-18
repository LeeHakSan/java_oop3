package io.ch15_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class ScoreStorage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== 시험 점수 저장소 =====");
        System.out.print("1. 점수 저장 | 2. 결과 분석 : ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            insertScore(sc);
        } else if (choice.equals("2")) {
            sumScore();
        }

        sc.close();
    }

    public static void insertScore(Scanner sc) {
        System.out.print("학생 수를 입력하세요 : ");
        try {
            int count = Integer.parseInt(sc.nextLine()); // 문자열 값을 int 변환
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                System.out.print((i+1) + "번째 학생 점수 : ");
                // sb에 계속 append
                // 10공백20공백30공백
                String score = sc.nextLine();
                sb.append(score);
                sb.append(" ");
            }

            try (FileOutputStream fos = new FileOutputStream("score.txt")) {
                fos.write(sb.toString().getBytes());
                System.out.println("저장 완료!");
            }


        } catch (Exception e) {
            throw new RuntimeException();
        }


    }

    public static void sumScore() {

        try (FileInputStream fis = new FileInputStream("score.txt")) {
            StringBuilder sb = new StringBuilder();
            int data;
            int total = 0;

            while ((data = fis.read()) != -1) {
                sb.append((char) data);
            }
            // 공백 기준으로 문자열을 자름 --> 배열 반환
            String[] parts = sb.toString().trim().split(" ");
            for (String part : parts) {
                total += Integer.parseInt(part);
            }

            System.out.println("총점 : " + total);
            System.out.println("평균 : " + (double)total/parts.length);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
