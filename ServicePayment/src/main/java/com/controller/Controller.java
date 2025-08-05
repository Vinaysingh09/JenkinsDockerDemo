package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.request.PaymentRequest;
import com.service.IPaymentService;

@RestController
@RequestMapping(value = "/pay")
public class Controller {
	private final IPaymentService paymentService;
	
	public Controller(IPaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	@GetMapping(value = "/service")
	public String getService() {
		return "Payment Service";
	}
	
	@PostMapping(value = "/process")
	public PaymentRequest ProcesPaymentService(PaymentRequest req) {
		return paymentService.processPayment(req);
	}
}
