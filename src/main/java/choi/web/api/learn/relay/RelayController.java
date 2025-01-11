package choi.web.api.learn.relay;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
public class RelayController {

    /*
        1. 흐름
        - QR > Platform > A or B or C 호출

        2. 응답값
        - A
        {"result":true,"payload":{"code":"0000","rank":[{"site":"site_0","rank":0},{"site":"site_1","rank":1},{"site":"site_2","rank":2}]}}

        - B
        {"result":true,"payload":{"error_code":"0000","hasTicket":true}}

        - C
        {"result":true,"payload":{"result":0,"membership":"VIP"}}
     */

    @GetMapping("/relay/qr")
    public Map<String, Object> qrSystem(@RequestParam String serviceType) throws IOException {
        log.info("serviceType = {}", serviceType);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/relay/platform";

        Relay relay = new Relay();
        relay.setServiceType(serviceType);
        if ("a".equals(serviceType)) {
            Map<String, Object> param = new HashMap<>();
            param.put("ids", Arrays.asList(1, 2, 3));

            relay.setPayload(param);
        }

        Map<String, Object> map = restTemplate.postForObject(
                url,
                relay,
                Map.class
        );
        log.info("[QR] map = {}", map);

        ObjectMapper mapper = new ObjectMapper();
        log.info("[QR] response = {}", mapper.writeValueAsString(map));

        return map;
    }

    @PostMapping("/relay/platform")
    public Map<String, Object> platformSystem(@RequestBody Relay relay) throws IOException {
        log.info("relay = {}", relay);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/relay/" + relay.getServiceType();

        // a 시스템으로 중계할 때는, DB에서 조회해서 고정으로 보내야하는 Body를 더한다고 가정.
        if ("a".equals(relay.getServiceType())) {
            String jsonStrInDataBase = """
                    {
                      "isActive": true,
                      "name": "Alice",
                      "roles": ["admin", "user", "editor"]
                    }
                    """;

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(jsonStrInDataBase, Map.class);
            log.info("[Platform] InDataBase Map = {}", map);

            relay.getPayload().putAll(map);
        }
        log.info("[Platform] 최종 relay = {}", relay);

        Map<String, Object> map = restTemplate.postForObject(
                url,
                relay,
                Map.class
        );
        log.info("[Platform] map = {}", map);

        Map<String, Object> result = new HashMap<>();
        result.put("result", true);
        result.put("payload", map);
        log.info("[Platform] result = {}", result);

        return result;
    }

    @PostMapping("/relay/a")
    public Map<String, Object> aSystem(@RequestBody Map<String, Object> param) throws IOException {
        log.info("[A] param = {}", param);

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

    @PostMapping("/relay/b")
    public Map<String, Object> bSystem(@RequestBody Map<String, Object> param) throws IOException {
        log.info("[B] param = {}", param);

        Map<String, Object> map = new HashMap<>();
        map.put("error_code", "0000");
        map.put("hasTicket", true);

        return map;
    }

    @PostMapping("/relay/c")
    public Map<String, Object> cSystem(@RequestBody Map<String, Object> param) throws IOException {
        log.info("[C] param = {}", param);

        Map<String, Object> map = new HashMap<>();
        map.put("result", 0);
        map.put("membership", "VIP");

        return map;
    }

}
