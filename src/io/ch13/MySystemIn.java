package io.ch13;

import java.io.IOException;

/**
 * 표준 입출력이란
 * 자바에서 표준 입출력은 프로그램과 사용자 간의 기본적인 데이터 교환 방법을 제공합니다
 */
public class MySystemIn {
    public static void main(String[] args) {
        System.out.println("알파벳 하나를 쓰고 enter 키를 누르세요");
        int i;

        try {
            i = System.in.read();
            // 주의 : 한글(3바이트)은 처리불가 -> 깨진 문자나 의미 없는 정수값이 나옴
            System.out.println("--------------------------------------");
            System.out.println(i);
            System.out.println((char) i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
