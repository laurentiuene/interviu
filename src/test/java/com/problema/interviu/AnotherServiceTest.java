package com.problema.interviu;

import com.problema.interviu.model.Account;
import com.problema.interviu.repository.AccountRepository;
import com.problema.interviu.service.test.AnotherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest()
public class AnotherServiceTest {

    @Autowired
    AnotherService service;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void test() {
        accountRepository.save(new Account("A", 100));
        accountRepository.save(new Account("B", 100));
        try {
            service.outer();
        } catch (Exception ignored) {

        }
        System.out.println("A = " + accountRepository.findById("A").get().getBalance());
        System.out.println("B = " + accountRepository.findById("B").get().getBalance());
    }


}
