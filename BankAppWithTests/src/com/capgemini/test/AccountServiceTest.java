package com.capgemini.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
import com.capgemini.service.AccountServiceImpl;

public class AccountServiceTest {
	
	@Mock
	AccountRepository accountRepository;
	
	
	AccountServiceImpl accountService ;
	
	
	
	@Before
	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
		accountService	=  new AccountServiceImpl(new AccountRepositoryImpl());
		
	}
	
	/* CreateAccount Test cases
	 * 1. When Valid information in passed account should be created successfully
	 * 2. When the amount is less than five thousand should throw InsufficientInitialBalanceException
	 * 3. When the account is already exist system should throw InvalidAccountNumberException
	 * 4. When Zero value of account number or amount is passed should throw BlankAccountInformationException 
	 * 5. When Blank value of any customer information is passed should throw BlankCustomerInformationException
	 * 
	 */
	
	
	
	
	@Test
	public void whenValidInformationInPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialBalanceException, InvalidAccountNumberException, BlankAccountInformationException, BlankCustomerInformationException {
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		
		Account actualAccount = accountService.createAccount(123, 6000, customer);
		
		assertEquals(account, actualAccount);
		
	}
	
	
	@Test(expected = com.capgemini.exception.InsufficientInitialBalanceException.class)
	public void whenTheAmountIsLessThanFiveThousandShouldThrowInsufficientInitialBalanceException() throws InsufficientInitialBalanceException, InvalidAccountNumberException, BlankAccountInformationException, BlankCustomerInformationException {
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
		accountService.createAccount(123, 400, customer);
		
	}
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenAccountIsAlreadyExistShouldThrowInvalidAccountNumberException() throws InsufficientInitialBalanceException, InvalidAccountNumberException, BlankAccountInformationException, BlankCustomerInformationException {
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
		accountService.createAccount(123, 6000, customer);
		
	}
	
	
	@Test (expected =  com.capgemini.exception.BlankAccountInformationException.class)
	public void whenZeroValueOfAccountNumberOrAmountIsPassedShouldThrowBlankAccountInformationException() throws InsufficientInitialBalanceException, InvalidAccountNumberException, BlankAccountInformationException, BlankCustomerInformationException {
		accountService.createAccount(0, 0, new Customer());
	}
	
	
	@Test (expected = com.capgemini.exception.BlankCustomerInformationException.class) 
	public void whenBlankValueOfAnyCustomerInformationIsPassedShouldThrowBlankCustomerInformationException() throws InsufficientInitialBalanceException, InvalidAccountNumberException, BlankAccountInformationException, BlankCustomerInformationException{
		
		 Customer customer =  new Customer();
		 customer.setFirstName("");
		 customer.setCity("");
		 customer.setLastName("");
		 accountService.createAccount(123, 4000, customer);
		
	}
	
	/*DepositAmount test cases
	 * 1. When valid account number and amount is passed amount should get deposited
	 * 2. When invalid account number is passed should throw InvalidAccountNumberException
	 * 3. When negative amount value is passed should get InvalidAmountException
	 * 4. When Blank value of any customer information is passed should throw BlankCustomerInformationException
	 */
	
	
	@Test
	public void whenValidAccountNumberAndAmountIsPassedAmountShouldGetDeposited() throws InsufficientInitialBalanceException, InvalidAccountNumberException, InvalidAmountException, BlankAccountInformationException, BlankCustomerInformationException {
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
//		Mockito.when(accountRepository.updateAccount(account)).thenReturn(account);
		Account accountObj = accountService.depositAmount(123, 1000);
		account.setAccontBalance(account.getAccontBalance()+1000);
		assertEquals(account, accountObj);
		
	}
	
	
	@Test (expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenInvalidAccountNumberIsPassedShouldThrowInvalidAccountNumberException() throws InsufficientInitialBalanceException, InvalidAccountNumberException, InvalidAmountException, BlankAccountInformationException, BlankCustomerInformationException {
		
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
//		Mockito.when(accountRepository.updateAccount(account)).thenReturn(account);
		accountService.depositAmount(1234, 1000);
	}
	
	@Test (expected = com.capgemini.exception.InvalidAmountException.class)
	public void whenNegativeAmountValueIsPassedShouldGetInvalidAmountException() throws InvalidAccountNumberException, InvalidAmountException, BlankAccountInformationException {
		accountService.depositAmount(1234, -1000);
	}
	
	
	@Test (expected =  com.capgemini.exception.BlankAccountInformationException.class)
	public void whenZeroValueOfAccountNumberOrAmountIsPassedShouldThrowBlankAccountInformationExceptionForDepositAmount() throws InvalidAccountNumberException, BlankAccountInformationException, InvalidAmountException {
		accountService.depositAmount(0, 0);
	}
	
	
	/* WithdrawAmout test cases
	 * 1. When valid account number and amount is passed amount should get withdraw
	 * 2. When invalid account number is passed should throw InvalidAccountNumberException
	 * 3. When negative amount value is passed should get InvalidAmountException
	 * 4. When amount passed is greater than available account balance should throw InsufficientBalanceException
	 * 5. When Blank value of any customer information is passed should throw BlankCustomerInformationException
	 */
	
	@Test
	public void whenValidAccountNumberAndAmountIsPassedAmountShouldGetWithraw() throws InsufficientInitialBalanceException, InvalidAccountNumberException, InvalidAmountException, BlankAccountInformationException, BlankCustomerInformationException, InsufficientBalanceException {
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
//		Mockito.when(accountRepository.updateAccount(account)).thenReturn(account);
		Account accountObj = accountService.withDrawAmount(123, 1000);
		account.setAccontBalance(account.getAccontBalance()-1000);
		assertEquals(account, accountObj);
		
	}
	
	
	@Test (expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenInvalidAccountNumberIsPassedShouldThrowInvalidAccountNumberExceptionInWithdraw() throws InsufficientInitialBalanceException, InvalidAccountNumberException, InvalidAmountException, BlankAccountInformationException, BlankCustomerInformationException, InsufficientBalanceException {
		
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
//		Mockito.when(accountRepository.updateAccount(account)).thenReturn(account);
		accountService.withDrawAmount(1234, 1000);
	}
	
	@Test (expected=com.capgemini.exception.InvalidAmountException.class)
	public void whenNegativeAmountValueIsPassedShouldGetInvalidAmountExceptionInWithdraw() throws InvalidAccountNumberException, InvalidAmountException, BlankAccountInformationException, InsufficientBalanceException {
		accountService.withDrawAmount(1234, -1000);
	}
	
	@Test (expected = com.capgemini.exception.InsufficientBalanceException.class) 
	public void  whenAmountPassedIsGreaterThanAvailableAccountBalanceShouldThrowInsufficientBalanceException() throws InsufficientInitialBalanceException, InvalidAccountNumberException, BlankAccountInformationException, BlankCustomerInformationException, InvalidAmountException, InsufficientBalanceException {
		Account account = new Account();
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
		account.setAccontBalance(6000);
		account.setAccountNumber(123);
		account.setCustomer(customer);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
//		Mockito.when(accountRepository.updateAccount(account)).thenReturn(account);
		accountService.withDrawAmount(123, 7000);
	}
	
	
	@Test (expected =  com.capgemini.exception.BlankAccountInformationException.class)
	public void whenZeroValueOfAccountNumberOrAmountIsPassedShouldThrowBlankAccountInformationExceptionForDepositAmountForWithdrwAmount() throws InvalidAccountNumberException, BlankAccountInformationException, InvalidAmountException, InsufficientBalanceException {
		accountService.withDrawAmount(0, 0);
	}
	
	
	/* Fund transfer test cases
	 * 1. When valid information is passed amount should be transfered to from account to to account and updated list of account should be returned.
	 * 2. When invalid from account is passed should throw InvalidAccountNumberException.
	 * 3. When invalid to account is passed should throw InvalidAccountNumberException.
	 * 4. When transfer amount is greater than available balance in from account should throw InsufficientBalanceException.
	 * 5. When negative amount value is passed should get InvalidAmountException
	 * 6. When zero from account number or to account number is passed should throw BlankAccountInformationException
	 * 
	 */
	
	
	@Test
	public void whenValidInformationIsPassedAmountShouldBeTransferedToFromAccountToToAccountAndUpdatedListOfAccountShouldBeReturned() throws InsufficientInitialBalanceException, InvalidAccountNumberException, InsufficientBalanceException, BlankAccountInformationException, BlankCustomerInformationException, InvalidAmountException {
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
		Account fromaccount = new Account();
		fromaccount.setAccontBalance(6000);
		fromaccount.setAccountNumber(123);
		fromaccount.setCustomer(customer);
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
		
		Customer customerNew = new Customer();
		customerNew.setFirstName("Sachin");
		customerNew.setLastName("Jadhav");
		customerNew.setCity("Pune");
		Account toaccount = new Account();
		toaccount.setAccontBalance(7000);
		toaccount.setAccountNumber(456);
		toaccount.setCustomer(customerNew);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(456, 7000, customer);
		
		List<Account>accountList = accountService.fundTransfer(123, 456, 1000);
		
		fromaccount.setAccontBalance(fromaccount.getAccontBalance()-1000);
		toaccount.setAccontBalance(toaccount.getAccontBalance()+1000);
		assertEquals(fromaccount, accountList.get(0));
		assertEquals(toaccount, accountList.get(1));
		
	}
	
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenInvalidFromAccountIsPassedShouldThrowInvalidAccountNumberException() throws InsufficientInitialBalanceException, InvalidAccountNumberException, InsufficientBalanceException, BlankAccountInformationException, BlankCustomerInformationException, InvalidAmountException {
		
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
		Account fromaccount = new Account();
		fromaccount.setAccontBalance(6000);
		fromaccount.setAccountNumber(123);
		fromaccount.setCustomer(customer);
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
		
		Customer customerNew = new Customer();
		customerNew.setFirstName("Sachin");
		customerNew.setLastName("Jadhav");
		customerNew.setCity("Pune");
		Account toaccount = new Account();
		toaccount.setAccontBalance(7000);
		toaccount.setAccountNumber(456);
		toaccount.setCustomer(customerNew);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(456, 7000, customer);
		
		accountService.fundTransfer(1234, 456, 1000);
		
	}
	
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenInvalidToAccountIsPassedShouldThrowInvalidAccountNumberException() throws InsufficientInitialBalanceException, InvalidAccountNumberException, InsufficientBalanceException, BlankAccountInformationException, BlankCustomerInformationException, InvalidAmountException {
		
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
		Account fromaccount = new Account();
		fromaccount.setAccontBalance(6000);
		fromaccount.setAccountNumber(123);
		fromaccount.setCustomer(customer);
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
		
		Customer customerNew = new Customer();
		customerNew.setFirstName("Sachin");
		customerNew.setLastName("Jadhav");
		customerNew.setCity("Pune");
		Account toaccount = new Account();
		toaccount.setAccontBalance(7000);
		toaccount.setAccountNumber(456);
		toaccount.setCustomer(customerNew);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(456, 7000, customer);
		
		accountService.fundTransfer(123, 4567, 1000);	
	}
	
	@Test(expected = com.capgemini.exception.InsufficientBalanceException.class)
	public void whenTransferAmountIsGreaterThanAvailableBalanceInFromAccountShouldThrowInsufficientBalanceException() throws InsufficientInitialBalanceException, InvalidAccountNumberException, InsufficientBalanceException, BlankAccountInformationException, BlankCustomerInformationException, InvalidAmountException {
		Customer customer = new Customer();
		customer.setFirstName("RadheShyam");
		customer.setLastName("Tiwari");
		customer.setCity("Mumbai");
		Account fromaccount = new Account();
		fromaccount.setAccontBalance(6000);
		fromaccount.setAccountNumber(123);
		fromaccount.setCustomer(customer);
//		this.WhenValidInformationInPassedAccountShouldBeCreatedSuccessfully();
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(123, 6000, customer);
		
		Customer customerNew = new Customer();
		customerNew.setFirstName("Sachin");
		customerNew.setLastName("Jadhav");
		customerNew.setCity("Pune");
		Account toaccount = new Account();
		toaccount.setAccontBalance(7000);
		toaccount.setAccountNumber(456);
		toaccount.setCustomer(customerNew);
//		Mockito.when(accountRepository.saveAccount(account)).thenReturn(true);
		accountService.createAccount(456, 7000, customer);
		
		accountService.fundTransfer(123, 4567, 7000);	
	
	}
	
	@Test(expected = com.capgemini.exception.InvalidAmountException.class) 
	public void whenNegativeAmountValueIsPassedShouldGetInvalidAmountExceptionForFundTransfer() throws InvalidAccountNumberException, InsufficientBalanceException, BlankAccountInformationException, InvalidAmountException {
		
		accountService.fundTransfer(123, 456, -500);
		
	}
	
	@Test(expected = com.capgemini.exception.BlankAccountInformationException.class)
	public void whenZeroFromAccountNumberOrToAccountNumberIsPassedShouldThrowBlankAccountInformationException() throws InvalidAccountNumberException, InsufficientBalanceException, BlankAccountInformationException, InvalidAmountException {
		
		accountService.fundTransfer(0, 0, 7000);
		
	}
	
	

}
