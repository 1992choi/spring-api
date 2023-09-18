package choi.web.api.test.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WeatherController {

    // TODO: 2023/08/08. 코드 리팩토링
    @GetMapping("/test/weather")
    public Map testWeather() throws IOException {
        String key = "1234";
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String time = LocalDateTime.now().minusHours(1).format(DateTimeFormatter.ofPattern("HH")) + "00";
        log.info("date={}, time={}", date, time);

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key);
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode("55", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode("127", "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            log.info("line ={}", line);
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(sb.toString(), HashMap.class);

        String temperatures = "";
        String precipitation = "";
        String precipitationType = "";

        List<HashMap<String, Object>> list = (ArrayList) (((HashMap) (((HashMap) ((HashMap) map.get("response")).get("body")).get("items"))).get("item"));
        for (HashMap<String, Object> infoMap : list) {
//            log.info("================================================================");
//            log.info("날짜={}, 시간={}", infoMap.get("baseDate"), infoMap.get("baseTime"));
//            log.info("항목={}", infoMap.get("category"));
//            log.info("값={}", infoMap.get("obsrValue"));
            if ("T1H".equals(infoMap.get("category"))) {
                temperatures = String.valueOf(infoMap.get("obsrValue"));
            } else if ("RN1".equals(infoMap.get("category"))) {
                precipitation = String.valueOf(infoMap.get("obsrValue"));
            } else if ("PTY".equals(infoMap.get("category"))) {

                switch (String.valueOf(infoMap.get("obsrValue"))) {
                    case "0":
                        precipitationType = "맑음";
                        break;
                    case "1":
                        precipitationType = "비";
                        break;
                    case "2":
                        precipitationType = "비/눈";
                        break;
                    case "3":
                        precipitationType = "눈";
                        break;
                    case "5":
                        precipitationType = "빗방울";
                        break;
                    case "6":
                        precipitationType = "빗방울눈날림";
                        break;
                    case "7":
                        precipitationType = "눈날림";
                        break;
                    default:
                        precipitationType = "맑음";
                }
            }
        }

        log.info("온도={}, 강수량={}, 강수형태={}", temperatures, precipitation, precipitationType);
        sendTelegram(date, time, temperatures, precipitation, precipitationType);
        return map;
    }

    private void sendTelegram(String date, String time, String temperatures, String precipitation, String precipitationType) {
        String token = "1234";
        String chatId = "-918556582"; // 채팅방 ID 얻기 : https://api.telegram.org/bot{봇ID}/getUpdates
        StringBuffer sb = new StringBuffer();
        sb.append("조회날짜=").append(date);
        sb.append(", 조회시간=").append(time);
        sb.append(", 온도=").append(temperatures);
        sb.append(", 강수량=").append(precipitation);
        sb.append(", 강수형태=").append(precipitationType);

        BufferedReader in = null;

        try {
            URL obj = new URL("https://api.telegram.org/bot" + token + "/sendmessage?chat_id=" + chatId + "&text=" + URLEncoder.encode(sb.toString(), "UTF-8"));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                log.info("line={}", line);
            }
        } catch (Exception e) {
            log.error("sendTelegram Err", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

}