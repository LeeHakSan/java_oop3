package http.toJson;

import com.google.gson.Gson;
import lombok.Data;

public class ToJsonEx {
    public static void main(String[] args) {
        Post post = new Post();
        post.setUserId(1);
        post.setId(1);
        post.setTitle("자바 공부");
        post.setBody("JSON 파싱 공부");

        Gson gson = new Gson();
        String jsonPost = gson.toJson(post);
        System.out.println(jsonPost);

        // jsonPost를 역방향으로 변경
        Post rePost = gson.fromJson(jsonPost, Post.class);
        System.out.println(rePost);





    }
}

@Data
class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
}
