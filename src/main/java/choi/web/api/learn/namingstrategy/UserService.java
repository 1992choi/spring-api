package choi.web.api.learn.namingstrategy;

import choi.web.api.common.domain.User;
import choi.web.api.common.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 목록 조회
     */
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

}
