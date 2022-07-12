package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.user.CashAccount;

public interface CashAccountService {
    Integer getBalance(Long userId);
    void deposit(CashAccount cashAccount, int amount);
    void withdraw(CashAccount cashAccount, int amount);
}
