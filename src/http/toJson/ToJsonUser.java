package http.toJson;

import com.google.gson.Gson;
import http.parsing.Address;
import http.parsing.Company;
import http.parsing.Geo;
import http.parsing.User;

public class ToJsonUser {
    public static void main(String[] args) {
        User user = new User("abc","def", "ghi", "jkl", "mno",
                new Company("pqr","stu", "vwx"),
                new Address("yz", "aab", "bcc", "dde",
                        new Geo("eff", "ggh")));

        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        System.out.println(jsonUser);

        User newUser = gson.fromJson(jsonUser, User.class);
        System.out.println(newUser);

    }
}
