package com.coswafe.odyssey.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.coswafe.odyssey.constants.AppConstants;
import com.coswafe.odyssey.model.PaymentModel;
import com.coswafe.odyssey.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/payment")
	public ResponseEntity<?> uploadFile(HttpServletRequest request) {
		return ResponseEntity.ok(paymentService.openPaymentUrl());
	}

	@PostMapping("/payment-success")
	public RedirectView success(HttpServletRequest request, @Validated PaymentModel success) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(AppConstants.CLIENT_SUCCESS_URL);
		return redirectView;
	}

	@PostMapping("/payment-failure")
	public RedirectView failure(HttpServletRequest request, @Validated PaymentModel failure) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(AppConstants.CLIENT_FAILURE_URL);
		return redirectView;
	}

}
