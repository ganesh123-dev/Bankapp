package com.bankingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapplication.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
