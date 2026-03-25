package http.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class AirClient {

    private static final String SERVICE_KEY = "e055bdf1da82d7c3e0280b58fbbb47acb22f76080045a196317f30d9b9e71cbb";
    private static final String BASE_URL = "http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo";

    public static void main(String[] args) {


//        itemsList = gson.fromJson(getAirData(), typeToken.getType());
//        System.out.println(itemsList);



    } // end of main
    private static String getAirData () {
        HttpURLConnection connection;
        try {
            StringBuilder urlBuilder = new StringBuilder(BASE_URL); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + SERVICE_KEY); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("year","UTF-8") + "=" + URLEncoder.encode("2025", "UTF-8")); /*측정 연도*/
            urlBuilder.append("&" + URLEncoder.encode("itemCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략*/

            URL url = new URL(urlBuilder.toString());
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            int checkConnect = connection.getResponseCode();
            System.out.println("Response Code : " + checkConnect);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                String resultString = builder.toString();
                Gson gson = new Gson();
                return resultString;
            }



        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
} // end of class
