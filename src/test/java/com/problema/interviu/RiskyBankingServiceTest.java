package com.problema.interviu;

import com.problema.interviu.model.Account;
import com.problema.interviu.repository.AccountRepository;
import com.problema.interviu.service.RiskyBankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.problema.interviu.setup.Setup.setupRisky;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RiskyBankingServiceTest {

    @Autowired
    private RiskyBankingService service;

    @Autowired
    private AccountRepository repo;

    @Test
    void testProcessPayment_shouldWorkAfterFixes() {
        setupRisky(repo);

        // testul ar trebui să meargă fără excepții după fixuri
        assertDoesNotThrow(() ->
                service.processPayment("A", "B", 10)
        );

        Account a = repo.findById("A").orElseThrow();
        Account b = repo.findById("B").orElseThrow();

        assertTrue(a.getBalance() < 100);
        assertTrue(b.getBalance() > 0);
    }


}
