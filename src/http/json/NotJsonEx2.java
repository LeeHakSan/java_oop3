package http.json;

public class NotJsonEx2 {
    public static void main(String[] args) {
        String json = "{\"userId\": 1,\"id\": 1,\"title\": \"quidem molestiae enim\"}";
        // json 형식에서 문자열 파싱 해서 객체로 반환

        String step1 = json.replace("{", "").replace("}", "");
        System.out.println(step1 + "\n");

        String[] parts = step1.split(",");
        for (String part : parts) {
            System.out.println(part);
        }

        // userId parsing
        String userIdString = parts[0].split(":")[1];
        int userIdValue = Integer.parseInt(userIdString.trim());
        System.out.println("userId" + userIdValue);

        // id parsing
        String idString = parts[1].split(":")[1];
        int idValue = Integer.parseInt(idString.trim());
        System.out.println("id : " + idValue);

        // title parsing
        String titleString = parts[2].replace("\"", "");
        String titleValue = titleString.split(":")[1].trim();
        System.out.println(titleValue);

        System.out.println("----------------------");
//        Album album = new Album(userIdValue, idValue, titleValue);
//        album.toString();



    } // end of main
} // end of class
