package choi.web.api.learn.loosecoupling;

public interface DiscountPolicy {
    double discount(long price);
}
