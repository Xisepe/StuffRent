package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.model.client.user.CashAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashAccountEntityRepository extends JpaRepository<CashAccount, Long> {
    CashAccount findByUserId(Long userId);
}
