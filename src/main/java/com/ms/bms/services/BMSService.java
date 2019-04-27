package com.ms.bms.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.bms.controller.EventSearchController;
import com.ms.bms.controller.MovieBookingController;
import com.ms.bms.event.AbstractEvent;
import com.ms.bms.payment.IPaymentGateway;
import com.ms.bms.payment.PaymentGatewaySelector;
import com.ms.bms.transaction.MovieTransaction;
import com.ms.bms.transaction.Transaction;
import com.ms.bms.transaction.Transaction.Status;

@RestController
public class BMSService {
	Logger logger = LoggerFactory.getLogger(BMSService.class);

	@Autowired
	public MovieBookingController movieBookingController;
	@Autowired
	public EventSearchController searchController;

	@RequestMapping(value = "/bms/search/{name}", method = RequestMethod.GET)
	public List<AbstractEvent> search(@PathVariable String name) {
		return searchController.getEventsByName(name);
	}

	@RequestMapping(value = "/bms/initiateBooking", method = RequestMethod.POST, produces = { "application/json" })
	public Transaction.Status initiateBooking(@RequestBody @Valid final MovieTransaction transaction) {
		logger.info("Transaction: " + transaction);
		movieBookingController.preBook(transaction);
		return transaction.status;
	}

	@RequestMapping(value = "/bms/payandbook", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Transaction.Status payandbook(@RequestBody @Valid final MovieTransaction transaction) {
		IPaymentGateway paymentGateway = PaymentGatewaySelector.getPaymentGateway(transaction.paymentType);
		paymentGateway.transferMoney(transaction, transaction.amount);
		Status status = transaction.status;
		if (transaction.status == Status.PAYMENT_SUCCESS) {
			transaction.status = finalBook(transaction);
			status=transaction.status;
		} else if (transaction.status == Status.PRE_BOOK_FAIl) {
			cancel(transaction);
		}
		return status;
	}

	@RequestMapping(value = "/bms/cancel", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Transaction.Status cancel(@RequestBody @Valid final MovieTransaction transaction) {
		IPaymentGateway paymentGateway = PaymentGatewaySelector.getPaymentGateway(transaction.paymentType);
		
		if (transaction.status != Status.INIT || transaction.status != Status.PRE_BOOK_FAIl ) {
			movieBookingController.cancel(transaction);
			if (transaction.status == Status.COMPLETE || transaction.status == Status.PAYMENT_SUCCESS) {
				paymentGateway.revertMoney(transaction, transaction.amount);
			}
			transaction.status=Status.CANCELLED;
		}
		return transaction.status;
	}

	public Transaction.Status finalBook(MovieTransaction transaction) {
		logger.info("Transaction: " + transaction);
		movieBookingController.book(transaction);
		return transaction.status;
	}

}
