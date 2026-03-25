package http.api;

import lombok.Data;

import java.util.List;

@Data
public class WeatherParse {
    private Coord coord;
    private String name;
    private List<Weather> weather;
    private Main main;
    private Wind wind;

    @Data
    public static class Coord {
        private double lon;
        private double lat;
    }
    @Data
    public static class Weather {
        private String description;
    }
    @Data
    public static class Main {
        private double temp;
        private int humidity;
    }
    @Data
    public static class Wind {
        private double speed;
        private int deg;
    }
}
