package com.problema.interviu.controller;

import com.problema.interviu.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankingController {

    @Autowired
    private BankingService service;

    @PostMapping("/transfer")
    public void transfer() {
        service.transfer("A", "B", 50);
    }

    @PostMapping("/withdraw")
    public void withdraw() {
        service.withdraw("A", 200);
    }

    @PostMapping("/concurrent")
    public void concurrent() throws InterruptedException {
        Thread t1 = new Thread(() -> service.concurrentUpdate("A"));
        Thread t2 = new Thread(() -> service.concurrentUpdate("A"));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
