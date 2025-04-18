package choi.web.api.learn.loosecoupling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LooseCouplingController {

    private final OrderServiceImpl orderService;

    @GetMapping("/loose-coupling")
    public double looseCoupling() {
        return orderService.order(2000);
    }

}