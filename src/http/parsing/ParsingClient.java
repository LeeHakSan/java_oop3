package http.parsing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ParsingClient {
    public static void main(String[] args) {

        String urlString = "https://jsonplaceholder.typicode.com/users";
        HttpURLConnection connection;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            int connectResponse = connection.getResponseCode();
            System.out.println("Response Code : " + connectResponse);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder responseString = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    responseString.append(line);
                }
                String jsonString = responseString.toString();

                Gson gson = new Gson();
//                User user = gson.fromJson(jsonString, User.class);
//                System.out.println(user.getId());
//                System.out.println(user.getAddress().getStreet());
//                System.out.println(user.getAddress().getGeo().getLat());
                TypeToken<List<User>> typeToken = new TypeToken<>() {};
                List<User> userList = gson.fromJson(jsonString, typeToken.getType());

                for (int i = 0; i < userList.size(); i++) {
                    System.out.println(userList.get(i));
                }

            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
