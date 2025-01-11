package choi.web.api.learn.relay;

import lombok.Data;

import java.util.Map;

@Data
public class Relay {

    private String serviceType;

    private Map<String, Object> payload;

}
