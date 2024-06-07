package br.com.ecommerce.payment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import br.com.ecommerce.payment.PaymentApplicationTests.NoContext;

@ContextConfiguration(classes = NoContext.class)
@SpringBootTest
class PaymentApplicationTests {

	static class NoContext{}

	@Test
	void contextLoads() {
	}

}