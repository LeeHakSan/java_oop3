package http.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// https://jsonplaceholder.typicode.com/todos/1
public class SimpleHttpClient {
    public static void main(String[] args) {

        // 자바로 HTTP 요청과 응답 만들어보기
//        String urlString = "https://jsonplaceholder.typicode.com/todos/10";
        String urlString = "https://jsonplaceholder.typicode.com/posts/1";
        // HTTP를 통신하는 클래스
        HttpURLConnection connection = null;


        try {
            // 1단계 : URL객체 생성
            URL url = new URL(urlString);

            // 2단계 : HTTP 연결 객체 생성
            connection = (HttpURLConnection) url.openConnection();

            // 3단계 : 요청 방식 설정 (GET, POST, PUT, DELETE)
            // 자원의 요청 --> GET 던져야함

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // 4단계 : 실제 요청 전송 --> 서버 --> 즉시응답 --> (응답 코드 + 요청한 데이터 반환)
            int responseCode = connection.getResponseCode();
            System.out.println("응답 코드 확인 : " + responseCode);

            if (responseCode != 200) {
                System.out.println("요청 실패!");
                return;
            }

            // 5단계 : 응답 본문 읽기
            // I/O 단원에서 배운 체인 그대로 사용 가능
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

                // 문자가 많을 때 StringBUffer나 StringBuilder 를 쓰는 게 좋다
                StringBuffer response = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append("\n");
                }
                System.out.println("응답 내용 : ");
                System.out.println(response.toString());
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                // close() 랑 같은 구문
                connection.disconnect();
            }
        }


    } // end of main
}
