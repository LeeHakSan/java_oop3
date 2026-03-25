package http.api;

import lombok.Data;

import java.util.List;

@Data
public class AirQualityDto {

    private Response response;

    @Data
    public static class Response {
        private Header header;
        private Body body;
    }

    @Data
    public static class Header {
        private String resultMsg;
        private String resultCode;
    }

    @Data
    public static class Body {
        private int totalCount;
        private List<Item> items; // JSON Array 대응
        private int pageNo;
        private int numOfRows;
    }

    @Data
    public static class Item {
        private String clearVal;
        private String sn;
        private String districtName;
        private String dataDate;
        private String issueVal;
        private String issueTime;
        private String clearDate;
        private String issueDate;
        private String moveName;
        private String clearTime;
        private String issueGbn;
        private String itemCode;
    }
}