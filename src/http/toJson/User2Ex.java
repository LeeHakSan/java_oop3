package http.toJson;

import com.google.gson.Gson;

public class User2Ex {
    public static void main(String[] args) {
        User2 user2 = new User2();
        User2.Address address = new User2.Address();
        User2.Company company = new User2.Company();
        User2.Address.Geo geo = new User2.Address.Geo();

        user2.setAddress(address);
        user2.setCompany(company);
        user2.getAddress().setGeo(geo);

        // 값 할당할 수 있음
        Gson gson = new Gson();
        String user2Json = gson.toJson(user2);
        System.out.println(user2Json);

    }
}
