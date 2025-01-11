package choi.web.api.learn.testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ExtractingTest {

    @Test
    @DisplayName("성공 케이스")
    void extracting_success() {
        List<Response> list = new ArrayList<>();
        list.add(new Response(new Account(1L), new Corp(1L)));
        list.add(new Response(new Account(2L), null));
        list.add(new Response(new Account(3L), new Corp(1L)));
        list.add(new Response(new Account(4L), null));
        list.add(new Response(new Account(5L), new Corp(1L)));

        // 테스트
        assertAll(
                // account.id 검증
                () -> assertThat(list).extracting("account")
                        .extracting("accountId")
                        .isNotNull(),

                // corp.id 검증
                () -> assertThat(list)
                        .allSatisfy(response -> {
                            if (response.getCorp() != null) {
                                assertThat(response.getCorp().getCorpId()).isNotNull();
                            }
                        })
        );
    }

    @Test
    @DisplayName("실패 케이스")
    @Disabled
    void extracting_fail() {
        List<Response> list = new ArrayList<>();
        list.add(new Response(new Account(1L), new Corp(1L)));
        list.add(new Response(new Account(2L), null));
        list.add(new Response(new Account(3L), new Corp(1L)));
        list.add(new Response(new Account(4L), null));
        list.add(new Response(new Account(5L), new Corp(null)));

        // 테스트
        assertAll(
                // account.id 검증
                () -> assertThat(list).extracting("account")
                        .extracting("accountId").isNotNull(),

                // corp.id 검증
                () -> assertThat(list)
                        .allSatisfy(response -> {
                            if (response.getCorp() != null) {
                                assertThat(response.getCorp().getCorpId()).isNotNull();
                            }
                        })
        );
    }

}

class Response {

    private Account account;
    private Corp corp;

    // 생성자, getter, setter
    public Response(Account account, Corp corp) {
        this.account = account;
        this.corp = corp;
    }

    public Account getAccount() {
        return account;
    }

    public Corp getCorp() {
        return corp;
    }

}

class Account {

    private Long accountId;

    public Account(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

}

class Corp {

    private Long corpId;

    public Corp(Long corpId) {
        this.corpId = corpId;
    }

    public Long getCorpId() {
        return corpId;
    }

    public void setCorpId(Long corpId) {
        this.corpId = corpId;
    }

}

