package choi.web.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocsController {

    @GetMapping("/docs/member")
    public String memberDocs() {
        return "/docs/index.html";
    }

}
