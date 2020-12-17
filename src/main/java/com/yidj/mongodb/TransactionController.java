package com.yidj.mongodb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author danjun.yi
 * @since 2020/12/15
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Resource
    private TransactionService transactionService;

    @RequestMapping("/success")
    public String success(){
        transactionService.transaction();
        return "success";
    }

    @RequestMapping("/fail")
    public String fail(){
        transactionService.failTransaction();
        return "fail";
    }
}
