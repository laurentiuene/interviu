package com.problema.interviu.service;

import com.problema.interviu.model.Account;
import com.problema.interviu.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class RiskyBankingService {

    private final AccountRepository repo;

    public RiskyBankingService(AccountRepository repo) {
        this.repo = repo;
    }

    public void processPayment(String fromId, String toId, Integer amount) {

        Account from = repo.findById(fromId).orElse(null);
        Account to = repo.findById(toId).orElse(null);

        int fee = calculateFee(from);
        int total = amount + fee;
        validateTransaction(amount, total);

        int riskScore = computeRiskScore(amount, from.getBalance());

        from.setBalance(from.getBalance() - total);
        to.setBalance(to.getBalance() + amount - riskScore);

        repo.save(from);
        repo.save(to);
    }

    private int calculateFee(Account acc) {
        return acc.getBalance() > 100 ? 2 : 5;
    }

    private void validateTransaction(int amount, int total) {
        if (amount <= 0 || total > 1000) {
            throw new IllegalArgumentException("Transaction rejected");
        }
    }

    private int computeRiskScore(int amount, int balance) {
        return amount / balance;
    }
}