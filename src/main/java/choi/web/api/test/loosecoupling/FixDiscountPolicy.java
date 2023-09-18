package choi.web.api.test.loosecoupling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FixDiscountPolicy implements DiscountPolicy {

    @Override
    public double discount(long price) {
        log.info("FixDiscountPolicy 호출");
        log.info("판매가격 = {}", price);
        log.info("할인 적용 가격 = {}", price - 100);
        return price - 100;
    }

}
