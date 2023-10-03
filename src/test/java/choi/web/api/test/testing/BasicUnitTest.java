package choi.web.api.test.testing;

import org.junit.jupiter.api.*;

public class BasicUnitTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("## beforeAll() 수행");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("## afterAll() 수행");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("## beforeEach() 수행");
    }

    @AfterEach
    void afterEach() {
        System.out.println("## afterEach() 수행");
    }

    @Test
    @DisplayName("성공 케이스")
    void success() {
        System.out.println("## success() 수행");
        int sum = 5;
        Assertions.assertEquals(1 + 4, sum);
    }

    @Test
    @DisplayName("실패 케이스")
    void fail() {
        System.out.println("## fail() 수행");
        int sum = 5;
        Assertions.assertEquals(1 + 10, sum);
    }

}
