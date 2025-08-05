package com.service;

import com.model.PaymentModel;
import com.repository.PaymentRepository;
import com.request.PaymentRequest;

public class PaymentService implements IPaymentService {
	private final PaymentRepository paymentRepository;

	public PaymentService(PaymentRepository paymentRepository) {
		super();
		this.paymentRepository = paymentRepository;
	}

	@Override
	public PaymentRequest processPayment(PaymentRequest req) {
		// TODO Auto-generated method stub
		PaymentModel pay = new PaymentModel();
		pay.setAmount(req.getAmount());
		pay.setQuantity(req.getQuantity());
		pay.setUserId(req.getUserId());
		paymentRepository.save(pay);
		return req;
	}

}
