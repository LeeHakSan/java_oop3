package io.ch17_1.array;

import java.util.ArrayList;

public class ArrayListEx {


    public static void main(String[] args) {
        // ArrayList는 배열의 불편함을 해결해주는 클래스이다.
        ArrayList<String> list = new ArrayList<>(); // 크기를 미리 지정할 필요 없음
        // 추가 하는 기능
        list.add("사과"); // 내부적으로 [0] 인덱스 자동 저장
        list.add("바나나"); // 내부적으로 [1] 인덱스 자동 저장
        list.add("포도"); // 내부적으로 [2] 인덱스 자동 저장

        // 조회 하는 기능
        System.out.println(list.get(0));
//        System.out.println(list.get(100)); 오류 발생
        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        // 삭제 하는 기능
//        list.remove("바나나");
//        list.remove("사과");
//        list.remove("포도");
//        list.remove("포도11111111111"); 없는 데이터 라면 무시, 예외 X
//        list.removeAll();
        list.clear(); // 전체 삭제

        // 편의 기능
        System.out.println("현재 ArrayList의 길이 " + list.size());
        System.out.println(list.contains("사과"));
        System.out.println(list.isEmpty());

    }


}
