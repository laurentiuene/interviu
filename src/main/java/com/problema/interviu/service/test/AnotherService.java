package com.problema.interviu.service.test;

import com.problema.interviu.model.Account;
import com.problema.interviu.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnotherService {

    private final AccountRepository repo;

    public AnotherService(AccountRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void outer() {
        Account a = repo.findById("A").orElseThrow();
        a.setBalance(a.getBalance() + 100);

        try {
            inner();
        } catch (Exception e) {
            System.out.println("Inner failed");
        }
    }

    @Transactional
    public void inner() {
        Account b = repo.findById("B").orElseThrow();
        b.setBalance(b.getBalance() + 100);

        throw new RuntimeException("Boom");
    }
}
