package br.com.ecommerce.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.payment.model.Payment;
import br.com.ecommerce.payment.model.PaymentDTO;
import br.com.ecommerce.payment.model.PaymentStatus;
import br.com.ecommerce.payment.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	
	public void createPayment(PaymentDTO dto) {
		Payment payment = new Payment(
				dto.orderId(), 
				dto.userId(), 
				dto.paymentAmount(), 
				PaymentStatus.AWAITING);
		
		repository.save(payment);
	}
}