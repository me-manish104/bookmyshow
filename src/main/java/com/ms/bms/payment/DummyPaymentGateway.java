package com.ms.bms.payment;

import javax.validation.Valid;

import com.ms.bms.transaction.MovieTransaction;
import com.ms.bms.transaction.Transaction;

public class DummyPaymentGateway implements IPaymentGateway {

	@Override
	public boolean transferMoney(Transaction transaction, double amount) {
		transaction.status=Transaction.Status.PAYMENT_SUCCESS;
		return true;
	}

	@Override
	public boolean revertMoney(@Valid MovieTransaction transaction, double amount) {
		// TODO Auto-generated method stub
		return true;
	}

}
