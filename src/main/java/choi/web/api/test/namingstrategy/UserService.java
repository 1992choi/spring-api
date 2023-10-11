package choi.web.api.test.namingstrategy;

import choi.web.api.domain.User;
import choi.web.api.repository.jpa.UserRepository;
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
