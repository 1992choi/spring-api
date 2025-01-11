package choi.web.api.learn.resilience;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ResilienceController {

    final private ResilienceService resilienceService;

    @GetMapping("/resilience/circuitbreaker")
    public void circuitbreaker(@RequestParam String type) {
        resilienceService.circuitbreaker(type);
    }

}
