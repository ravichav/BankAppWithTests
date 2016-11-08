package com.capgemini.repository;

import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public interface AccountRepository {
	
	boolean saveAccount(Account account);
	Account updateAccount(Account account);
	Account searchAccount(int accountNumber);

}
