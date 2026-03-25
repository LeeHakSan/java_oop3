package http.parsing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class User {

    private String name;
    private String userName;
    private String email;
    private String phone;
    private String webSite;
    private Company company;
    private Address address;

}
