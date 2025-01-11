package choi.web.api.learn.loosecoupling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl {

    private final DiscountPolicy discountPolicy;

    public double order(long price) {
        return discountPolicy.discount(price);
    }

}
