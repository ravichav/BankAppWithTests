package com.capgemini.model;

public class Account {
	
	int accountNumber;
	int accontBalance;
	Customer customer;
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAccontBalance() {
		return accontBalance;
	}
	public void setAccontBalance(int accontBalance) {
		this.accontBalance = accontBalance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber + accontBalance;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber || accontBalance != other.getAccontBalance())
			return false;
		return true;
	}

}
