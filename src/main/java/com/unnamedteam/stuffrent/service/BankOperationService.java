package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.user.CashAccount;

public interface BankOperationService {
    void startTransaction(CashAccount cashAccount);
    void endTransaction(CashAccount cashAccount);
}
