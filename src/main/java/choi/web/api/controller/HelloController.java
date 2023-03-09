package choi.web.api.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/hello-get/{name}")
    public String getMethod(@PathVariable String name) {
        return "Hello GET : " + name;
    }

    @PostMapping("/hello-post")
    public String postMethod(@RequestParam String name) {
        return "Hello Post : " + name;
    }

    @PutMapping("/hello-put")
    public String putMethod(@RequestBody Map<String, String> map) {
        return "Hello Put : " + map.get("name");
    }

    @DeleteMapping("/hello-delete")
    public String deleteMethod(@RequestBody HelloUser helloUser) {
        return "Hello Delete : " + helloUser.getName();
    }

}

@Data
class HelloUser {
    String name;
}
