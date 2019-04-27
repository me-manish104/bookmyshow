package com.ms.bms.payment;

public class PaymentGatewaySelector {
	public static IPaymentGateway getPaymentGateway(PaymentType type) {
		if (PaymentType.DUMMY == type) {
			return new DummyPaymentGateway();
		}
		return null;
	}
}
