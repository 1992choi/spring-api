package choi.web.api.learn.versioning;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VersionController {

    @GetMapping("/v1/versioning-uri")
    public ResponseEntity uriV1() {
        return ResponseEntity.ok("URI 방식 v1");
    }

    @GetMapping("/v2/versioning-uri")
    public ResponseEntity uriV2() {
        return ResponseEntity.ok("URI 방식 v2");
    }

    @GetMapping(value = "/versioning-param", params = "version=1")
    public ResponseEntity paramV1() {
        return ResponseEntity.ok("파라미터 방식 v1");
    }

    @GetMapping(value = "/versioning-param", params = "version=2")
    public ResponseEntity paramV2() {
        return ResponseEntity.ok("파라미터 방식 v2");
    }

    @GetMapping(value = "/versioning-header", headers = "X-API-VERSION=1")
    public ResponseEntity headerV1() {
        return ResponseEntity.ok("헤더 방식 v1");
    }

    @GetMapping(value = "/versioning-header", headers = "X-API-VERSION=2")
    public ResponseEntity headerV2() {
        return ResponseEntity.ok("헤더 방식 v2");
    }

    @GetMapping(value = "/versioning-mime", produces = "application/vnd.company.appv1+json")
    public ResponseEntity mimeV1() {
        return ResponseEntity.ok("MIME 방식 v1");
    }

    @GetMapping(value = "/versioning-mime", produces = "application/vnd.company.appv2+json")
    public ResponseEntity mimeV2() {
        return ResponseEntity.ok("MIME 방식 v2");
    }

}
