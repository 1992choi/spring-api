package choi.web.api.learn.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncFacade asyncFacade;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/sync")
    public String sync() {
        long startTime = System.currentTimeMillis();
        asyncFacade.sync();

        return String.valueOf(System.currentTimeMillis() - startTime);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/async")
    public String async() {
        long startTime = System.currentTimeMillis();
        asyncFacade.async();

        return String.valueOf(System.currentTimeMillis() - startTime);
    }

}