package com.bankingapplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapplication.dto.AccountDto;
import com.bankingapplication.service.AccountService;

//We have to annotate the controller class with @RestController
//@RestController annotation makes this class as a spring mvc controller class
@RestController
@RequestMapping("/api/accounts")  //@RequestMapping is used to define a base URL
public class AccountController {
	
	//Injecting the dependency
	private AccountService accountService;
	//Let us use the constructor based dependency injection to inject this accountService dependency
	//Here we don't need to use the @Autowired for dependency injection. Spring will take care of it for a constructor with one filed
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
	//Add Account REST API
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	
	//Get Account REST API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountDto= accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	
	//Deposit REST API
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable long id, @RequestBody Map<String, Double> request){
		//Note: Here 2 parameters are used, long id is for primary key to get the account. 
		//Map<String, Double> is used, because in postman we pass the JSON data as {"amount": 4000.0}
		//To make this we are using amount as key(amount) and value as double(4000.0). 
		//which is a key,value pair data. So we used Map as the datatype.
		AccountDto accountDto= accountService.deposit(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);
	}
	
	
	//Withdraw REST API
	@PutMapping("{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable long id, @RequestBody Map<String, Double> request){
		AccountDto accountDto= accountService.withdraw(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);
	}
	
	
	//Get all accounts REST API
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts= accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	
	//Delete REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delById(@PathVariable long id){
		accountService.deleteById(id);
		return ResponseEntity.ok("Account Deleted Sucussfully");
	}
	
	//Print Message
	@GetMapping("/one")
	public String check() {
		return "Hello Nagaraju";
	}
	
}
