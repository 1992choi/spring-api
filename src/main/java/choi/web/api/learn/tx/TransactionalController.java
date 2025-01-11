package choi.web.api.learn.tx;

import choi.web.api.common.domain.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionalController {

    private final TransactionService transactionService;

    @GetMapping("/tx/case-1")
    public ResponseData case1() throws Exception {
        transactionService.case1();
        return new ResponseData("0000", "성공하였습니다.");
    }

    @GetMapping("/tx/case-2")
    public ResponseData case2() throws Exception {
        transactionService.case2();
        return new ResponseData("0000", "성공하였습니다.");
    }

    @GetMapping("/tx/case-3")
    public ResponseData case3() throws Exception {
        transactionService.case3();
        return new ResponseData("0000", "성공하였습니다.");
    }

    @GetMapping("/tx/case-4")
    public ResponseData case4() throws Exception {
        transactionService.case4();
        return new ResponseData("0000", "성공하였습니다.");
    }

    @GetMapping("/tx/case-5")
    public ResponseData case5() throws Exception {
        transactionService.case5();
        return new ResponseData("0000", "성공하였습니다.");
    }

    @GetMapping("/tx/case-6")
    public ResponseData case6() throws Exception {
        transactionService.case6();
        return new ResponseData("0000", "성공하였습니다.");
    }

    @GetMapping("/tx/case-7")
    public ResponseData case7() throws Exception {
        transactionService.case7();
        return new ResponseData("0000", "성공하였습니다.");
    }

    @GetMapping("/tx/case-8")
    public ResponseData case8() throws Exception {
        transactionService.case8();
        return new ResponseData("0000", "성공하였습니다.");
    }

}
