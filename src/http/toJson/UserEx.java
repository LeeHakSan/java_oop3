package http.toJson;

public class UserEx {
    public static void main(String[] args) {
        // userDTO 클래스를 내부 클래스에 필드를 만들었을 때
        User user = new User();
        user.setName("홍길동");
        user.setEmail("safffsdfs1@naver,com");

        // User 안에 있는 Address 객체를 생성하는 방법
        User.Address address = user.new Address();
        address.setCity("부산");
        address.setStreet("중앙대로");

        User.Address.Geo geo = user.getAddress().new Geo();
        address.setGeo(geo);
        geo.setLat("15.24");
        geo.setLng("54.15");




    }
}
