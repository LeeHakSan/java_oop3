package http.http_client;

import com.google.gson.Gson;
import http.http_client.dtos.Post;

import javax.sound.sampled.Port;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * 기존 데이터를 수정(PUT)하고, 수정된 결과를 객체로 파싱하여 확인해보자.
 */
public class HttpPutParsingEx {

    public static void main(String[] args) {
        // https://jsonplaceholder.typicode.com/posts/1
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        // 통신을 요청하는 코드에서는 거의 대부분 예외처리를 해 주어 한다.
        try {
            Post updatePost = Post.builder()
                    .id(1)
                    .title("수정된 제목")
                    .body("내용을 수정 합니다")
                    .userId(10)
                    .build();
            String jsonRequest = gson.toJson(updatePost);

            // HTTP 요청 메세지 생성
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                    .header("Content-Type", "application/json")
                    // 서버측으로 보낼 타입을 명시 하는 곳(HTTP body 영역)
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            // 서버측으로 요청 전송 및 응답
            // 1 번째 인수값 : 내가 만들어 둔 HTTP 요청 메세지 객체
            // 2 번째 인수값 : 서버가 보내준 데이터를 어떤 형태로 받을 것인가 명시 (String)
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
            System.out.println(response.statusCode()); // 응답코드 (200)ok

            if (response.statusCode() == 200) {
                System.out.println("body: " + response.body());
                Post resultPost = gson.fromJson(response.body(), Post.class);

                System.out.println(resultPost.getId());
                System.out.println(resultPost.getBody());
            } else {
                System.out.println("잘못된 요청 또는 응답");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}