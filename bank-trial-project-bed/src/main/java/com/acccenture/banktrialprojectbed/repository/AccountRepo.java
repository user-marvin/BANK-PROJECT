package com.acccenture.banktrialprojectbed.repository;

import com.acccenture.banktrialprojectbed.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Integer> {
}
