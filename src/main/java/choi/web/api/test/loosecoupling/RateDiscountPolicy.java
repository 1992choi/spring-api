package choi.web.api.test.loosecoupling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public double discount(long price) {
        log.info("RateDiscountPolicy 호출");
        log.info("판매가격 = {}", price);
        log.info("할인 적용 가격 = {}", price - (price * 0.1));
        return price - (price * 0.1);
    }

}
