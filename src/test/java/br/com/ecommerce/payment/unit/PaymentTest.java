package br.com.ecommerce.payment.unit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.ecommerce.payment.model.Payment;
import br.com.ecommerce.payment.model.PaymentStatus;

class PaymentTest {

    @Test
    @DisplayName("Payment creation with valid values should not throw exception")
    void createPaymentTest01() {
        assertDoesNotThrow(() -> new Payment(1L, 1L, BigDecimal.TEN));
    }
    @Test
    @DisplayName("Payment creation with negative amount should throw IllegalArgumentException")
    void createPaymentTest02() {
        // act and assert
        assertThrows(IllegalArgumentException.class, 
            () -> new Payment(1L, 1L, new BigDecimal("-10")));
    }
    @Test
    @DisplayName("Payment creation with null values should throw IllegalArgumentException")
    void createPaymentTest03() {
        assertThrows(IllegalArgumentException.class, () -> new Payment(null, 1L, new BigDecimal("1")));
        assertThrows(IllegalArgumentException.class, () -> new Payment(1L, null, new BigDecimal("1")));
        assertThrows(IllegalArgumentException.class, () -> new Payment(1L, 1L, null));
        assertThrows(IllegalArgumentException.class, () -> new Payment(null, null, null));
        assertDoesNotThrow(() -> new Payment(1L, 1L, BigDecimal.TEN));
    }

    @Test
    @DisplayName("Update payment status - Cannot update from Canceled state")
    void updatePaymentStatusTest01() {
        Payment payment = Payment.builder()
                .orderId(1L)
                .userId(1L)
                .status(PaymentStatus.CANCELED)
                .paymentAmount(BigDecimal.TEN)
                .build();

        assertThrows(IllegalArgumentException.class, () -> payment.updatePaymentStatus(PaymentStatus.AWAITING));
        assertThrows(IllegalArgumentException.class, () -> payment.updatePaymentStatus(PaymentStatus.CONFIRMED));
    }

    @Test
    @DisplayName("Update payment status - Allowed update from Awaiting state")
    void updatePaymentStatusTest02() {
        Payment payment = Payment.builder()
                .orderId(1L)
                .userId(1L)
                .status(PaymentStatus.AWAITING)
                .paymentAmount(BigDecimal.TEN)
                .build();

        assertDoesNotThrow(() -> payment.updatePaymentStatus(PaymentStatus.AWAITING));
    }
}