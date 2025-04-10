package com.bankingapplication.mapper;

import com.bankingapplication.dto.AccountDto;
import com.bankingapplication.entity.Account;

public class AccountMapper {
	
	//Converting AccountDto into Account JPA Entity
	public static Account mapToAccount(AccountDto accountDto){
		Account account = new Account(
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
				);
		return account;
		
	}
	
	//Converting JPA Entity into AccountDto
	public static AccountDto mapToAccountDto(Account account) {
		AccountDto accountDto= new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		return accountDto;
	}
	

}
