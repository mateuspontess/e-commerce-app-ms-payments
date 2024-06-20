package br.com.ecommerce.payment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import br.com.ecommerce.payment.testcontainers.MySQLTestContainerConfig;
import br.com.ecommerce.payment.testcontainers.RabbitMQTestContainerConfig;

@SpringBootTest
@TestPropertySource(properties = "classpath:application-test.properties")
@ContextConfiguration(classes = {MySQLTestContainerConfig.class, RabbitMQTestContainerConfig.class})
class PaymentApplicationTests {

	@Test
	void contextLoads() {
	}
}