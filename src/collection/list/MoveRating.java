package collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MoveRating {
    public static void main(String[] args) {
        List<String> movies = new Vector<>();

        movies.add("범죄도시 4 - ****");
        movies.add("기생충 - *****");
        movies.add("올드보이 - ****");
        movies.add("태권V - ***");

        System.out.println("===== 전체 영화 목록 =====");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(i + ". " + movies.get(i));
        }

        System.out.println("\n총 " + movies.size() + "편");

        // 올드보이 삭제
        movies.remove("올드보이 - ****");
//        movies.remove(1);
        System.out.println("\n삭제 후 총 " + movies.size() + "편");

        // "기생충" 이 있는지 확인
        System.out.println("기생충 존재 여부 확인 : " + movies.contains("기생충 - *****"));





    } // end of main
}
