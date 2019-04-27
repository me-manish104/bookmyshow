package com.ms.bms.transaction;

import java.util.List;

import com.ms.bms.event.MovieEvent;

public class MovieTransaction extends Transaction {
public List<String> seatLocation;
public String screenName;
@Override
public String toString() {
	return "MovieTransaction [seatLocation=" + seatLocation + ", screenName=" + screenName + ", transactionTime="
			+ transactionTime + ", amount=" + amount + ", NumberOfSeats=" + NumberOfSeats + ", paymentType="
			+ paymentType + ", status=" + status + "]";
}

}
