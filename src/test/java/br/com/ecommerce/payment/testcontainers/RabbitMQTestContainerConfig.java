package br.com.ecommerce.payment.testcontainers;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@TestConfiguration
public class RabbitMQTestContainerConfig {
    
    @Bean
    @DynamicPropertySource
	public RabbitMQContainer getRabbitContainer(DynamicPropertyRegistry registry) {
        RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:3.7.25-management-alpine")
		    .withExposedPorts(5672, 15672);

        registry.add("spring.rabbitmq.port", () -> rabbit.getAmqpPort());
		registry.add("eureka.client.enabled", () -> false);

        return rabbit;
    }
}