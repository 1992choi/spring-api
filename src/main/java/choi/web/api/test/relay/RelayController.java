package choi.web.api.test.relay;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class RelayController {

    /*
        1. 흐름
        - QR > Platform > A or B or C 호출

        2. 응답값
        - A
        {"result":true,"response":{"code":"0000","rank":[{"site":"site_0","rank":0},{"site":"site_1","rank":1},{"site":"site_2","rank":2}]}}

        - B
        {"result":true,"response":{"error_code":"0000","hasTicket":true}}

        - C
        {"result":true,"response":{"result":0,"membership":"VIP"}}
     */

    @GetMapping("/relay/qr")
    public Map<String, Object> qrSystem(@RequestParam String infoType) throws IOException {
        log.info("infoType ={}", infoType);

        URL url = new URL("http://localhost:8080/relay/platform/" + infoType);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(sb.toString(), HashMap.class);
        log.info("[QR] map ={}", map);

        return map;
    }

    @GetMapping("/relay/platform/{systemCode}")
    public Map<String, Object> platformSystem(@PathVariable String systemCode) throws IOException {
        log.info("systemCode ={}", systemCode);

        URL url = new URL("http://localhost:8080/relay/" + systemCode);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> response = mapper.readValue(sb.toString(), HashMap.class);
        log.info("[Platform] response ={}", response);

        Map<String, Object> result = new HashMap<>();
        result.put("result", true);
        result.put("response", response);

        return result;
    }

    @GetMapping("/relay/a")
    public Map<String, Object> aSystem() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> info = new HashMap<>();
            info.put("site", "site_" + i);
            info.put("rank", i);

            list.add(info);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("code", "0000");
        map.put("rank", list);

        return map;
    }

    @GetMapping("/relay/b")
    public Map<String, Object> bSystem() {
        Map<String, Object> map = new HashMap<>();
        map.put("error_code", "0000");
        map.put("hasTicket", true);

        return map;
    }

    @GetMapping("/relay/c")
    public Map<String, Object> cSystem() {
        Map<String, Object> map = new HashMap<>();
        map.put("result", 0);
        map.put("membership", "VIP");

        return map;
    }

}
