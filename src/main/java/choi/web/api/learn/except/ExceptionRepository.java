package choi.web.api.learn.except;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExceptionRepository {

    // Checked Exception - 예외로 인해 종속 발생
    public void findAllWithChecked() throws SQLException {
        runSQL();
    }

    // Unchecked Exception - Checked Exception을 Unchecked Exception으로 변경하여 종속 제거
    public void findAllWithUnChecked() {
        try {
            runSQL();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e); // Checked Exception -> Unchecked Exception 전환
        }
    }

    private void runSQL() throws SQLException {
        throw new SQLException();
    }

}

class SqlRuntimeException extends RuntimeException {

    public SqlRuntimeException(Throwable cause) {
        super(cause);
    }

}
