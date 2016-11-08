package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.BlankAccountInformationException;
import com.capgemini.exception.BlankCustomerInformationException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.exception.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public interface AccountService {

	Account createAccount(int accountNumber, int amount, Customer customer) throws InsufficientInitialBalanceException, InvalidAccountNumberException, BlankAccountInformationException, BlankCustomerInformationException;
	Account depositAmount(int accountNumber,int amount) throws InvalidAccountNumberException, InvalidAmountException, BlankAccountInformationException;
	Account withDrawAmount (int accountNumber,int amount) throws InvalidAmountException, InvalidAccountNumberException, BlankAccountInformationException, InsufficientBalanceException;
	List<Account> fundTransfer(int fromAccountNumber, int toAccountNumber, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException, BlankAccountInformationException, InvalidAmountException;

}
