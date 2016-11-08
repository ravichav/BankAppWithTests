package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.exception.BlankAccountInformationException;
import com.capgemini.exception.BlankCustomerInformationException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.exception.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.AccountRepository;
import com.capgemini.repository.AccountRepositoryImpl;

public class AccountServiceImpl implements AccountService {
	
	AccountRepository accountRepository;
	
	 public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account createAccount(int accountNumber, int amount, Customer customer) throws InsufficientInitialBalanceException, InvalidAccountNumberException, BlankAccountInformationException, BlankCustomerInformationException {
	
		validateAccount(accountNumber,amount);
		validateCustomer(customer);
		
		if(amount<500) {
			throw new InsufficientInitialBalanceException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAccontBalance(amount);
		account.setCustomer(customer);
		if(!accountRepository.saveAccount(account)) {
			throw new InvalidAccountNumberException();
		}
		return account;
		
	}

	@Override
	public Account depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException, InvalidAmountException, BlankAccountInformationException {
	
			validateAccount(accountNumber, amount);
			if (amount<0) {
				throw new InvalidAmountException();
			}
			
			Account account = accountRepository.searchAccount(accountNumber);
			
			if (null==account) {
				 throw new InvalidAccountNumberException();
			}
			
			account.setAccontBalance(account.getAccontBalance() + amount);
			
			account = accountRepository.updateAccount(account);
		
			return account;
	}

	@Override
	public Account withDrawAmount(int accountNumber, int amount) throws InvalidAmountException, InvalidAccountNumberException, BlankAccountInformationException, InsufficientBalanceException {
		validateAccount(accountNumber, amount);
		if (amount<0) {
			throw new InvalidAmountException();
		}
		
		Account account = accountRepository.searchAccount(accountNumber);
		
		if (null==account) {
			 throw new InvalidAccountNumberException();
		}
		
		if (account.getAccontBalance()<amount) {
			throw new InsufficientBalanceException();
		}
		account.setAccontBalance(account.getAccontBalance() - amount);
		
		account = accountRepository.updateAccount(account);
	
		return account;
	}
	
	@Override
	public List<Account> fundTransfer(int fromAccountNumber , int toAccountNumber , int amount) throws InvalidAccountNumberException, InsufficientBalanceException, BlankAccountInformationException, InvalidAmountException {
		
		if(fromAccountNumber==0 || toAccountNumber==0) {
			throw new BlankAccountInformationException();
		}
		
		if(amount < 0){
			throw new InvalidAmountException();
		}
		Account fromAccount = accountRepository.searchAccount(fromAccountNumber);
		
		if (null==fromAccount) {
			 throw new InvalidAccountNumberException();
		} else if (fromAccount.getAccontBalance()<amount) {
			throw new InsufficientBalanceException();
		}
		
		Account toAccount = accountRepository.searchAccount(toAccountNumber);
		
		if (null==toAccount) {
			 throw new InvalidAccountNumberException();
		} 
		
		
		fromAccount.setAccontBalance(fromAccount.getAccontBalance()-amount);
	    fromAccount = accountRepository.updateAccount(fromAccount);
	    
	    toAccount.setAccontBalance(toAccount.getAccontBalance()+amount);
	    toAccount = accountRepository.updateAccount(toAccount);
		
	    List<Account> accountList =  new ArrayList<>();
	    accountList.add(fromAccount);
	    accountList.add(toAccount);
	    
	    return accountList;
	}
	
	
	private void validateAccount(int accountNumber, int amount) throws BlankAccountInformationException {
		if(accountNumber==0 || amount==0) {
			throw new BlankAccountInformationException();
		}
		
	}
	
	private void validateCustomer(Customer customer) throws BlankCustomerInformationException {
		if(null==customer) {
			throw new BlankCustomerInformationException();
		}
		
		if(null==customer.getFirstName() || customer.getFirstName().trim().equals("") 
		|| null==customer.getLastName() || customer.getLastName().trim().equals("")
		|| null==customer.getCity() || customer.getCity().trim().equals("")) {
			throw new BlankCustomerInformationException();
		}
	}

}
