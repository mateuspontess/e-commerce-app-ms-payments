package br.com.ecommerce.payment.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecommerce.payment.service.PaymentService;

@Component
public class PaymentListener {
	
	@Autowired
	private PaymentService service;

}