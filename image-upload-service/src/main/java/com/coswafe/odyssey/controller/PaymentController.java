package com.coswafe.odyssey.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coswafe.odyssey.service.PaymentService;

@RestController
public class PaymentController {

//	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/payment")
	public ResponseEntity<?> uploadFile(HttpServletRequest request) {
		return ResponseEntity.ok(paymentService.openPaymentUrl());
	}
}
