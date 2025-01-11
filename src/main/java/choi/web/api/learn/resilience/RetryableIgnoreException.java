package choi.web.api.learn.resilience;

public class RetryableIgnoreException extends RuntimeException {

    public RetryableIgnoreException(String message) {
        super(message);
    }

}