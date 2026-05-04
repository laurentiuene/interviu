package com.problema.interviu.setup;

import com.problema.interviu.model.Account;
import com.problema.interviu.repository.AccountRepository;

public class Setup {

    public static void setupRisky(AccountRepository repo) {
//        repo.save(new Account("A", 100));
        repo.save(new Account("B", 0));
    }
}
