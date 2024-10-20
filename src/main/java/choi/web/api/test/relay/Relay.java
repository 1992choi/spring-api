package choi.web.api.test.relay;

import lombok.Data;

import java.util.Map;

@Data
public class Relay {

    private String serviceType;

    private Map<String, Object> payload;

}
