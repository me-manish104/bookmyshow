package com.ms.bms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.bms.dao.BMSMovieEventDao;
import com.ms.bms.transaction.MovieTransaction;
import com.ms.bms.transaction.Transaction;

@Component
public class MovieBookingController implements EventController {
	Logger logger = LoggerFactory.getLogger(MovieBookingController.class);
	@Autowired
	public BMSMovieEventDao movieEventDao;
	
    @Override
	public void preBook(Transaction transaction) {
    	movieEventDao.block((MovieTransaction)transaction);
		transaction.status=Transaction.Status.PRE_BOOK_SUCCESS;
		
	}
	

	@Override
	public void book(Transaction transaction) {
		movieEventDao.markSuccess((MovieTransaction)transaction);
		transaction.status=Transaction.Status.COMPLETE;
		
	}
	
	@Override
	public void cancel(Transaction transaction) {
		movieEventDao.cancel((MovieTransaction)transaction);
		transaction.status=Transaction.Status.CANCELLED;
		
	}

}
