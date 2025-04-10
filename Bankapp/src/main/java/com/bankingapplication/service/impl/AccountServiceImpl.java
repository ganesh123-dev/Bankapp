package com.bankingapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bankingapplication.dto.AccountDto;
import com.bankingapplication.entity.Account;
import com.bankingapplication.mapper.AccountMapper;
import com.bankingapplication.repository.AccountRepository;
import com.bankingapplication.service.AccountService;


//We used @servcie annotaion to automatically create spring bean for the class
@Service
public class AccountServiceImpl implements AccountService{
	
	//Injecting the dependency
	private AccountRepository accountRepository;
	
	// Here we have to annotate with @Autowired to automatically inject the dependencies.
	// But form spring 4.3 onwards we can ignore this @Autowired annotation
	// When ever spring finds a single constructor in a spring bean, then spring will automatically inject the dependencies.
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount= accountRepository.save(account);
		
		//converting savedAccount into AccountDTO
		return AccountMapper.mapToAccountDto(savedAccount);
		//return accountDto;
	}


	@Override
	public AccountDto getAccountById(Long Id) {
		// TODO Auto-generated method stub
		Account account =accountRepository.findById(Id).orElseThrow(()->new RuntimeException("Account Does Not Exists"));
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposit(Long Id, double amount) {
		// TODO Auto-generated method stub
		//1.Checking account is present or not
		Account account =accountRepository.findById(Id).orElseThrow(()->new RuntimeException("Account Does Not Exists"));
		//2.depositing(adding) the amount to that account using get method.
		double total=account.getBalance()+amount;
		//3.updating the balance using set method.
		account.setBalance(total);
		//4.making change in the database using jpa method i.e., .save
		Account savedAccount= accountRepository.save(account);
		//5.returning the updated data to the user in postman console
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long Id, double amount) {
		// TODO Auto-generated method stub
		Account account =accountRepository.findById(Id).orElseThrow(()->new RuntimeException("Account Does Not Exists"));
		
		
		if(account.getBalance()<amount)
		{
			throw new RuntimeException("Insufficient Balance");
		}
		
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount= accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts= accountRepository.findAll();
		
		List<AccountDto> result= accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		return result;
	}


	@Override
	public void deleteById(Long Id) {
		// TODO Auto-generated method stub
		Account account =accountRepository.findById(Id).orElseThrow(()->new RuntimeException("Account Does Not Exists"));
		accountRepository.deleteById(Id);
	}


}
