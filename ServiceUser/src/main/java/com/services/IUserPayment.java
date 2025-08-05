package com.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.common.model.OrderReceivedRequest;

@FeignClient(name = "payment-service",url = "http://payment-service:8080")
public interface IUserPayment {
    @PostMapping("/pay/process")
	OrderReceivedRequest processPayment(@RequestBody OrderReceivedRequest req);
}
