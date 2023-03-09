package choi.web.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {

    @ApiOperation(value = "호출 테스트")
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @ApiOperation(value = "GET 메서드 예제", notes = "@PathVariable을 사용한 예제")
    @GetMapping("/hello-get/{name}")
    public String getMethod(@ApiParam(value = "이름") @PathVariable String name) {
        return "Hello GET : " + name;
    }

    @ApiOperation(value = "POST 메서드 예제", notes = "@RequestParam을 사용한 예제")
    @PostMapping("/hello-post")
    public String postMethod(@ApiParam(value = "이름") @RequestParam String name) {
        return "Hello POST : " + name;
    }

    @ApiOperation(value = "PUT 메서드 예제", notes = "@RequestBody 및 Map을 사용한 예제")
    @PutMapping("/hello-put")
    public String putMethod(@ApiParam(value = "유저 맵") @RequestBody Map<String, String> map) {
        return "Hello PUT : " + map.get("name");
    }

    @ApiOperation(value = "DELETE 메서드 예제", notes = "@RequestBody 및 DTO를 사용한 예제")
    @DeleteMapping("/hello-delete")
    public String deleteMethod(@ApiParam(value = "유저 DTO") @RequestBody HelloUser helloUser) {
        return "Hello DELETE : " + helloUser.getName();
    }

}

@Data
class HelloUser {
    String name;
}
