package choi.web.api.test.loosecoupling;

public interface DiscountPolicy {
    double discount(long price);
}
