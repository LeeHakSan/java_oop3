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

public class PostClient {
    // https://jsonplaceholder.typicode.com/posts/1
    public static void main(String[] args) {
        String urlString = "https://jsonplaceholder.typicode.com/posts/1";
        HttpURLConnection connection;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            int responseHttp = connection.getResponseCode();
            System.out.println("응답 번호 : " + responseHttp);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuffer buffer = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String output = buffer.toString();
                Gson gson = new Gson();
                TypeToken<List<Post>> typeToken = new TypeToken<>() {};
                List<Post> postList = gson.fromJson(output, typeToken.getType());

                System.out.println("전체 갯수 : " + postList.size() + "개");

                for (int i = 0; i < postList.size(); i++) {
                    System.out.println(postList.get(i));
                }

            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    } // end of main
} // end of class
