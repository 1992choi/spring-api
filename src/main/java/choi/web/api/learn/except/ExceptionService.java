package choi.web.api.learn.except;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExceptionService {

    private final ExceptionRepository exceptionRepository;

    // Checked Exception - 예외로 인해 종속 발생
    public void findAllWithChecked() throws SQLException {
        exceptionRepository.findAllWithChecked();
    }

    // Unchecked Exception - Checked Exception을 Unchecked Exception으로 변경하여 종속 제거
    public void findAllWithUnChecked() {
        exceptionRepository.findAllWithUnChecked();
    }


}
