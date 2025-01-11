package choi.web.api.learn.namingstrategy;

import choi.web.api.common.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 사용자 목록 조회
     */
    @GetMapping("/naming-strategy")
    public List<User> findUsers() {
        return userService.findAllUser();
    }

}
