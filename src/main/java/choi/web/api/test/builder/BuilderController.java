package choi.web.api.test.builder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuilderController {

    @GetMapping("/test/account/{loginType}")
    public Account getAccount(@PathVariable String loginType) {
        // 중복되는 필드가 많을 경우 비효율적.
//        if ("id".equals(loginType)) {
//            return Account.builder()
//                    .id("abc")
//                    // many field
//                    .build();
//        } else {
//            return Account.builder()
//                    .email("abc@google.com")
//                    // many field
//                    .build();
//        }

        // Builder를 재사용하여 중복코드 제거
        Account.AccountBuilder builder = Account.builder();

        // 공통 필드 설정
        // builder.someField("value1");

        // 조건에 따라 특정 필드만 설정
        if ("id".equals(loginType)) {
            builder.id("abc");
        } else {
            builder.email("abc@google.com");
        }

        // 객체 생성
        return builder.build();
    }

}
