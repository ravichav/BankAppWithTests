package com.capgemini.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public class AccountRepositoryImpl implements AccountRepository{
	
	Set<Account> accountList =  new HashSet<Account>();

	@Override
	public boolean saveAccount(Account account) {
		// TODO Auto-generated method stub
		
		return accountList.add(account);
		
	}

	@Override
	public Account updateAccount(Account account) {
		Account accountReturn = null;
	for (Account accountObj : accountList) {
		if (null!=account && null!=accountObj && accountObj.getAccountNumber()==account.getAccountNumber()) {
			accountObj = account;
			accountReturn = accountObj;
			break;
		}
	}
	
	return accountReturn;
	
	}

	@Override
	public Account searchAccount(int accountNumber) {
		Account accountReturn = null;
		for (Account accountObj : accountList) {
			if (null!=accountObj && accountObj.getAccountNumber()==accountNumber) {
				accountReturn = accountObj;
				break;
			}
		}
		
		return accountReturn;
	}

	
	
	

}
