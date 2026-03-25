package http.json;

public class NotJsonEx {
    public static void main(String[] args) {
        // HTTP 통신으로 json형식의 데이터를 받았다 가정
        // json 형식은 자바에서 문자열 취급
        String json = "{\"name\" : \"철수\", \"age\" : 25 }";

        // 위 json 형식의 문자열을 우리 프로그램 내에서 사용을 하려면 파싱 처리를 해줘야한다

        // 단계 1 : 불필요한 기호 제거 (replace 사용)
        // 중괄호와 큰 따옴표 아무것도 없는 상태로 만들어본다.
        String step1 = json.replace("{", "").replace("}", "");
        System.out.println(step1);

        // 단계2 : 항목별 자르기 (split 사용)
        String[] parts = step1.split("," );
        System.out.println("size : " + parts.length);
        System.out.println(parts[0]);
        System.out.println(parts[1]);

        // 단계 3 : 데이터 추출 및 할당
        // 첫번째 조각에서 "name" : "철수" 기준에서 철수라는 값을 추출 (:) 기준
        String nameValue = parts[0].split(":")[1];
        System.out.println(nameValue);

        // 두번째 조각 추출 "age" : 25 에서 25 추출
        String ageString = parts[1].split(":")[1];
        System.out.println(ageString);
        int ageValue = Integer.parseInt(ageString.trim());
        System.out.println(ageValue);

        // 단계 4 : 자바 프로그램에서 이해하는 데이터 타입 -> 객체로 변환
        User user = new User(nameValue, ageValue);
        System.out.println(user.toString());





    }
} // end of class

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
} // end of User
