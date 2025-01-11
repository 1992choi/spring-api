package choi.web.api.learn.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CircuitBreakerLogger {

    public CircuitBreakerLogger(CircuitBreakerRegistry circuitBreakerRegistry) {
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(circuitBreaker -> {
            circuitBreaker.getEventPublisher()
                    .onStateTransition(event -> log.info("Circuit Breaker '{}' 상태 변경: {} -> {}",
                            event.getCircuitBreakerName(),
                            event.getStateTransition().getFromState(),
                            event.getStateTransition().getToState()));
        });
    }

}