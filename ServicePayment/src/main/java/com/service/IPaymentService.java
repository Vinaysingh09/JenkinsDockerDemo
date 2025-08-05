package com.service;

import com.request.PaymentRequest;

public interface IPaymentService {
	PaymentRequest processPayment(PaymentRequest req);
}
