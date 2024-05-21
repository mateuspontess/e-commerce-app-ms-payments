package br.com.ecommerce.payment.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@Entity(name = "Payment")
@Table(name = "payments")
public class Payment {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long orderId;
	private Long userId;
	private BigDecimal paymentAmount;
	
	@Setter
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	
	public Payment(Long orderId, Long userId, BigDecimal paymentAmount, PaymentStatus status) {
		checkNotNull(orderId, "orderId");
		checkNotNull(userId, "userId");
		checkNotNull(paymentAmount, "paymentAmount");
		checkNotNull(status, "status");
		
		this.orderId = orderId;
		this.userId = userId;
		this.paymentAmount = paymentAmount;
		this.status = status;
	}
	
	private void checkNotNull(Object attribute, String attributeName) {
		if (attribute == null) 
			throw new IllegalArgumentException("Cannot be null: " + attributeName);
		
	}
	
	public void updatePaymentStatus(PaymentStatus newStatus) {
		if(this.status == PaymentStatus.CANCELED) 
			throw new IllegalArgumentException("Canceled payments cannot be changed");
		
		this.status = newStatus;
	}
}