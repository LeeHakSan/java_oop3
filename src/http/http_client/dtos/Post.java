package http.http_client.dtos;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
    @Builder
    public Post(Integer id, Integer userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}