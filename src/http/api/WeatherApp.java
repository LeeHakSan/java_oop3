package http.api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {

    public static void main(String[] args) {
        String apiKey = "48f17fd9e73e60977fa6d8246d59eb27"; // 발급받은 API 키 입력
        String city = "Busan";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                "&appid=" + apiKey + "&units=metric&lang=kr";

        try {
            // 1. URL 객체 생성 및 연결 설정
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000); // 연결 타임아웃 5초
            conn.setReadTimeout(5000);    // 읽기 타임아웃 5초

            // 2. 응답 코드 확인 (200 OK 인지 체크)
            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                // 3. 입력 스트림을 통해 데이터 읽기
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 4. 결과 출력
                System.out.println("응답 성공!");
//                System.out.println(response.toString());
                Gson gson = new Gson();
                WeatherParse weatherParse = gson.fromJson(response.toString(), WeatherParse.class);

                System.out.println("상태 : " + weatherParse.getWeather().get(0).getDescription());
                System.out.println("기온 : " + weatherParse.getMain().getTemp() +"도");
                System.out.println("습도 : " + weatherParse.getMain().getHumidity() + "%");
                System.out.println("풍속 : " + weatherParse.getWind().getSpeed() + "m/s");

                if (weatherParse.getMain().getTemp() > 25) {
                    System.out.println("오늘 날씨가 더우니 시원한 옷차림으로 나가세요 ~~");
                } else if (weatherParse.getMain().getTemp() <= 25 && weatherParse.getMain().getTemp() > 10) {
                    System.out.println("날씨가 선선하니 좋습니다. 야외 활동을 즐겨요 ~~");
                } else if (weatherParse.getMain().getTemp() <= 10 ) {
                    System.out.println("날씨가 쌀쌀 하니 옷을 껴입으세요");
                }

            } else {
                System.out.println("호출 실패. 응답 코드: " + responseCode);
            }

            conn.disconnect();

            // 도전 문제 - 1. 파싱 처리 직접 하기
            // 출력값
            /**
             * 상태 : 맑음
             * 기온 : 18.5
             * 습도 : 42%
             * 풍속 : 2.1m/s
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}