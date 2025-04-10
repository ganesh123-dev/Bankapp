package com.bankingapplication.service;

import java.util.List;

import com.bankingapplication.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long Id);
	
	AccountDto deposit(Long Id, double amount);
	
	AccountDto withdraw(Long Id, double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteById(Long Id);

}
