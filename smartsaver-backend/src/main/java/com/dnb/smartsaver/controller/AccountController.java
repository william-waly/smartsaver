package com.dnb.smartsaver.controller;

import com.dnb.smartsaver.model.Account;
import com.dnb.smartsaver.repository.AccountRepository;
import com.dnb.smartsaver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Account createAccount(@RequestParam Long userId, @RequestParam(defaultValue = "0") BigDecimal balance) {
        Account account = new Account();
        account.setUser(userRepository.findById(userId).orElseThrow());
        account.setAccountNumber("NO" + System.currentTimeMillis());
        account.setBalance(balance);
        return accountRepository.save(account);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountRepository.findById(id).orElseThrow();
    }
}