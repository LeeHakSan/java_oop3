package http.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Weather3 {

    public static void main(String[] args) {

        // 1. 공공 데이터 포탈 인증키 (보통 Decoding 사용)
        String serviceKey = "cab942b8a0308a10451f39191b258eb2";
        //
        // 2. 조회에 필요한 파라미터 설정


        try {
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=Busan&appid="
                    + "cab942b8a0308a10451f39191b258eb2" + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}