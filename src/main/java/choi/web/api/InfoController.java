package choi.web.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of(
                "version", "0.0.1",
                "profile", activeProfile
        );
    }

}
