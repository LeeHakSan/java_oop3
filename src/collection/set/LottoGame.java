package collection.set;

import java.util.*;

public class LottoGame {
    public static void main(String[] args) {
        Set<Integer> lotto = new HashSet<>();
        Random random = new Random();


        // 6개가 될 때 까지 계속 추가
        while (lotto.size() < 6) {
            int number = random.nextInt(45) + 1; // 1~45
            lotto.add(number);
        }
        System.out.println("이번 주 로또 번호 : " +lotto);
        System.out.println("총 " + lotto.size() + "개");

        // 로또 번호를 오름차순으로 정렬하시오
        // ArrayList 생성자 안에 set 개열을 넣으면 자동으로 ArrayList 객체를 생성해준다
        List<Integer> sortedLotto = new ArrayList<>(lotto);
        Collections.sort(sortedLotto);

        System.out.println(sortedLotto);


    }
}
