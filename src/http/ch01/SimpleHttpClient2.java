package http.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

// https://jsonplaceholder.typicode.com/todos/1
public class SimpleHttpClient2 {
    public static void main(String[] args) {
        // 가짜 서버의 유저 10번의 정보를 요청하고 응답을 받고 콘솔창에 출력 하시오
        String urlString = "https://jsonplaceholder.typicode.com/users/10";
        HttpURLConnection connection = null;

        try {
            URL url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // 시작줄 요청을 보낼 때
            connection.setRequestProperty("Accept", "application/json"); // 헤더



            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                System.out.println("응답 내용 :");
                System.out.println(builder.toString());
            }


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }


    } // end of main
}
