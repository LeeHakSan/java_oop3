package http.json;

public class MakeJsonEx {
    public static void main(String[] args) {
        // 자바 프로그램에서 --> 서버측으로 데이터를 보내려면 json 형식의 텍스트를 가장 많이 사용
        Album album = new Album(1, 1, "안녕 반가워 나의 앨범이야");
        // {} JSON Object 형태로 변환해서 보내기

        // 1.
        StringBuffer sb = new StringBuffer();
        sb.append("{"); // 시작 중괄호
        sb.append("\"userId\":").append(album.getUserId()).append(",");
        sb.append("\"id\":").append(album.getId()).append(",");
        sb.append("\"title\":").append("\"" + album.getTitle() + "\"");
        sb.append("}");
        System.out.println(sb.toString());


    } // end of main
} // end of class
