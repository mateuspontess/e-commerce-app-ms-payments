package br.com.ecommerce.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
}