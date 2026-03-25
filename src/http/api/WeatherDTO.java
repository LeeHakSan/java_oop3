package http.api;

import lombok.Data;

import java.util.List;
@Data

public class WeatherDTO {
    private Response response;
    public static class Response {
        private Header header;
        private Body body;

    }

    @Data
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    public static class Body {
        private String dataType;
        private int pageNo;
        private int numOfPages;
        private int totalCount;
        private Items items;

    }

    @Data
    public static class Items {
        private List<Item> item;

    }

    @Data
    public static class Item {
        private String baseDate; // 발표 일자
        private String baseTime; // 발표 시각
        private String category; // 자료구분코드
        private int nx;
        private int ny;
        private String obsrValue;
    }


}

