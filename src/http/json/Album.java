package http.json;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor

class Album {
    private int userId;
    private int id;
    private String title;
}