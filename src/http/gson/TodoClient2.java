package http.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TodoClient2 {

    public static void main(String[] args) {
        // 통신할 주소 : https://jsonplaceholder.typicode.com/todos/1
        // 여러건 조회 -> JSON Object {[][][][]} 응답됨
        String urlString = "https://jsonplaceholder.typicode.com/todos";
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
                System.out.println("JSON Array 응답 : " + resonseBody);

                // Json Array 파싱
                // TypeToken : List<Todo> 처럼 제네릭 타입을 파싱할 때 사용
                Gson gson = new Gson();
                TypeToken<List<Todo>> typeToken = new TypeToken<>() {};
                List<Todo> todoList = gson.fromJson(jsonString, typeToken.getType());

                System.out.println("전체 갯수 : " + todoList.size() + "개");

                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println(todoList.get(i));
                }

            }


        } catch (MalformedURLException e) {
            System.out.println("통신 실패 : " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
