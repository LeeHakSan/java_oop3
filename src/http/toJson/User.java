package http.toJson;

import lombok.Data;

@Data
public class User {
    private String name;
    private String userName;
    private String email;
    private String phone;
    private String webSite;
    private Company company;
    private Address address;

    @Data
    class Company {
        private String name;
        private String catchPhrase;
        private String bs;
    }

    @Data
    class Address {
        private String street;
        private String suite;
        private String city;
        private String zipCode;
        private Geo geo;

        @Data
        class Geo {
            private String lat;
            private String lng;

        }
    }


}



