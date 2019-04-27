package com.ms.bms.payment;

import javax.validation.Valid;

import com.ms.bms.transaction.MovieTransaction;
import com.ms.bms.transaction.Transaction;

public interface IPaymentGateway {
	public boolean transferMoney(Transaction transaction, double amount);

	public boolean revertMoney(@Valid MovieTransaction transaction, double amount);
}
