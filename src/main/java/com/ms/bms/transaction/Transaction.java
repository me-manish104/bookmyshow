package com.ms.bms.transaction;

import java.time.ZonedDateTime;

import com.ms.bms.event.AbstractEvent.EventType;
import com.ms.bms.payment.PaymentType;

public abstract class Transaction {
	public EventType type;
	public ZonedDateTime transactionTime;
	public double amount;
	public int NumberOfSeats;
	public PaymentType paymentType;
	public Status status = Status.INIT;

	public static enum Status {
		INIT, PRE_BOOK_FAIl, PRE_BOOK_SUCCESS, PENDING_PAYMENT, PAYMENT_FAILED, PAYMENT_SUCCESS, CANCELLED, COMPLETE
	}
}
