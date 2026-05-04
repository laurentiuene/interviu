package com.problema.interviu.service;

import com.problema.interviu.model.Account;
import com.problema.interviu.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class BankingService {

    private final AccountRepository accountRepository;

    public BankingService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transfer(String from, String to, int amount) {

        Account a = accountRepository.findById(from).orElseThrow(() -> new RuntimeException("Entity not found!"));
        Account b = accountRepository.findById(to).orElseThrow(() -> new RuntimeException("Entity not found!"));

        a.setBalance(a.getBalance() - amount);

        if (a.getBalance() < 0) throw new RuntimeException("Negative account balance!");

        b.setBalance(b.getBalance() + amount);

        accountRepository.save(a);
        accountRepository.save(b);
    }

    public void withdraw(String id, int amount) {
        Account acc = accountRepository.findById(id).orElseThrow();
        acc.setBalance(acc.getBalance() - amount);
        accountRepository.save(acc);
    }

    public void concurrentUpdate(String id) {
        Account acc = accountRepository.findById(id).orElseThrow();

        int balance = acc.getBalance();
        balance += 10;

        acc.setBalance(balance);
        accountRepository.save(acc);
    }
}
