package choi.web.api.learn.resilience;

public class RetryableException extends RuntimeException {

    public RetryableException(String message) {
        super(message);
    }

}
