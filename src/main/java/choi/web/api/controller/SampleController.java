package choi.web.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class SampleController {

    /* HTTP Request Mapping 및 Swagger 샘플 */
    @ApiOperation(value = "GET 메서드 예제", notes = "@PathVariable을 사용한 예제")
    @GetMapping("/sample/get/{name}")
    public String getMethod(@ApiParam(value = "이름") @PathVariable String name) {
        return "GET : " + name;
    }

    @ApiOperation(value = "POST 메서드 예제", notes = "@RequestParam을 사용한 예제")
    @PostMapping("/sample/post")
    public String postMethod(@ApiParam(value = "이름") @RequestParam String name) {
        return "POST : " + name;
    }

    @ApiOperation(value = "PUT 메서드 예제", notes = "@RequestBody 및 Map을 사용한 예제")
    @PutMapping("/sample/put")
    public String putMethod(@ApiParam(value = "유저 맵") @RequestBody Map<String, String> map) {
        return "PUT : " + map.get("name");
    }

    @ApiOperation(value = "DELETE 메서드 예제", notes = "@RequestBody 및 DTO를 사용한 예제")
    @DeleteMapping("/sample/delete")
    public String deleteMethod(@ApiParam(value = "유저 DTO") @RequestBody SampleUser sampleUser) {
        return "DELETE : " + sampleUser.getName();
    }

    /* Logback 예제 */
    @GetMapping("/sample/log")
    public String log() {
        log.info("Call Log()");
        return "LOG SUCCESS";
    }

}

@Data
class SampleUser {
    String name;
}
