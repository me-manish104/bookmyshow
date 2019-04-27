package com.ms.bms.controller;

import com.ms.bms.transaction.Transaction;

public interface EventController {

	public void preBook(Transaction transaction);

	public void book(Transaction transaction);
	
	public void cancel(Transaction transaction);

}
