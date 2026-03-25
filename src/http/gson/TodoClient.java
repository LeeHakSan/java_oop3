package http.gson;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TodoClient {

    public static void main(String[] args) {
        // 통신할 주소 : https://jsonplaceholder.typicode.com/todos/1
        // 단건 조회 -> JSON Object {} 응답됨
        String urlString = "https://jsonplaceholder.typicode.com/todos/100";
        HttpURLConnection connection;


        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            // HTTP 요청 메세지 만들어서 --> 연결 요청
            connection.setRequestMethod("GET");
            // 기본적인 설정 구축 되어 있음

            // 바로 연결 요청
            int responseHttpCode = connection.getResponseCode(); // 통신
            System.out.println("통신 성공 여부 확인 : " + responseHttpCode);

            // 응답 본문 추출
            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuffer resonseBody = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    resonseBody.append(line);
                }
                //String Buffer --> 문자열로 형 변환 toString() 호출, "" 문자열 더하기
                String jsonString = resonseBody.toString();
                System.out.println("JSON 응답 : " + resonseBody);
                // 자바 프로그램에서 사용하려면 JSON 형식의 텍스트를 파싱 해야함
                // GSON 라이브러리 사용 - 사용방법
                Gson gson = new Gson();
                // Java 객체 변환 <-- json 문자열
                // 변환 하고자 하는 json 형식의 문자열 <-- 1번째 인수값
                // 변환 하고자 하는 DTO 타입 클래스

                Todo todo = gson.fromJson(jsonString, Todo.class);
                // Gson 라이브러리를 활용해서 간단히 파싱하고 자바에서 쓰는 데이터 타입으로 활용 가능
                System.out.println("------------------");
                System.out.println(todo.getUserId());
                System.out.println(todo.getId());
                System.out.println(todo.getTitle());
                System.out.println(todo.toString());
            }


        } catch (MalformedURLException e) {
            System.out.println("통신 실패 : " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
