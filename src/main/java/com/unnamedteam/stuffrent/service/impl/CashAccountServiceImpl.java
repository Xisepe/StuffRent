package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.exeptions.NotEnoughMoneyOnBalanceException;
import com.unnamedteam.stuffrent.model.client.user.CashAccount;
import com.unnamedteam.stuffrent.repository.CashAccountEntityRepository;
import com.unnamedteam.stuffrent.service.BankOperationService;
import com.unnamedteam.stuffrent.service.CashAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class CashAccountServiceImpl implements CashAccountService {

    private CashAccountEntityRepository repository;
    private BankOperationService bankOperationService;

    @Override
    public Integer getBalance(Long userId) {
        return repository.findByUserId(userId).getAmount();
    }

    @Override
    public void deposit(CashAccount cashAccount, int amount) {
        bankOperationService.startTransaction(cashAccount);
        cashAccount.setAmount(cashAccount.getAmount() + amount);
        repository.save(cashAccount);
        bankOperationService.endTransaction(cashAccount);
    }

    @Override
    public void withdraw(CashAccount cashAccount, int amount) {
        bankOperationService.startTransaction(cashAccount);
        if (!checkBalance(cashAccount, amount))
            throw new NotEnoughMoneyOnBalanceException("Not enough money on balance to end transaction");
        repository.save(cashAccount);
        bankOperationService.endTransaction(cashAccount);
    }

    private boolean checkBalance(CashAccount cashAccount, int amount) {
        return cashAccount.getAmount() - amount >= 0;
    }
}
