package collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapEx {
    public static void main(String[] args) {
        // 학생 이름, 점수
        Map<String, Integer> scores = new HashMap<>();

        // 추가 (put)
        scores.put("철수", 90);
        scores.put("영희", 85);
        scores.put("민준", 92);

        // 조회 (get)
        System.out.println(scores.get("철수")); // 90 - 키 값으로 접근하면 value가 나옴
        System.out.println(scores.get("없는 값")); // null

        // 포함 여부
        System.out.println(scores.containsKey("영희")); // 키 값이 존재한다면 true
        System.out.println(scores.containsValue(85)); // 있는 값 true
        System.out.println(scores.containsValue(10)); // 없는 값 false

        // 삭제
        scores.remove("민준");

        // 크기
        System.out.println(scores.size());

        // put()은 덮어 씌우기도 한다
        scores.put("철수", 0);

        System.out.println(">>> " + scores.keySet());
        System.out.println(scores);
        for (String name : scores.keySet()) {
            System.out.println(name + " : " + scores.get(name) + " 점수");
        }


    } // end of main

}
