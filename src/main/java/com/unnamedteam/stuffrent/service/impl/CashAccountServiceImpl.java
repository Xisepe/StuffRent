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
    public CashAccount findCashAccountByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

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
        checkBalanceIfEnough(cashAccount, amount);
        repository.save(cashAccount);
        bankOperationService.endTransaction(cashAccount);
    }

    @Override
    public void checkBalanceIfEnough(CashAccount cashAccount, int amount) {
        if (amount > cashAccount.getAmount()) {
            throw new NotEnoughMoneyOnBalanceException("На вашем счету недосточно средств");
        }
    }

    @Override
    public void transferRentBegin(CashAccount getAccount, CashAccount sendAccount, int amount) {
        withdraw(sendAccount, amount);
    }

    @Override
    public void transferRentEnd(CashAccount getAccount, CashAccount sendAccount, int amount) {
        deposit(getAccount, amount);
    }

    private boolean checkBalance(CashAccount cashAccount, int amount) {
        return cashAccount.getAmount() - amount >= 0;
    }
}
