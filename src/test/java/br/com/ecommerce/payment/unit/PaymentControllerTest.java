package br.com.ecommerce.payment.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ecommerce.payment.controller.PaymentController;
import br.com.ecommerce.payment.model.Payment;
import br.com.ecommerce.payment.model.PaymentDTO;
import br.com.ecommerce.payment.service.PaymentService;

@WebMvcTest(PaymentController.class)
@AutoConfigureJsonTesters
class PaymentControllerTest {

	@MockBean
	private PaymentService service;
	@MockBean
	private RabbitTemplate template;

	@Autowired
	private MockMvc mvc;

	// json testers
	@JsonIgnoreProperties(ignoreUnknown = true)
    private record ConvertPagePaymentDTO(@JsonProperty("content") List<PaymentDTO> data) {};
	@Autowired
	private JacksonTester<ConvertPagePaymentDTO> listPaymentDTOJson;


	@Test
	void getAllTest01() throws IOException, Exception {
		// arrange
		Page<PaymentDTO> mockValueReturned = new PageImpl<>(List.of(new PaymentDTO(1L, 1L, BigDecimal.TEN)));
		when(service.getAllByParams(any(), any(), any(), any(), any(), any())).thenReturn(mockValueReturned);

		// act
		var result = mvc.perform(get("/payments")
			.accept(MediaType.APPLICATION_JSON)
			).andReturn().getResponse();

		// assert
		var responseBody = listPaymentDTOJson.parseObject(result.getContentAsString()).data().get(0);
		assertEquals(responseBody, mockValueReturned.getContent().get(0));
	}

	@Test
	void confirmPaymentTest01() throws IOException, Exception {
		// arrange
		Payment mockValueReturned = new Payment(1l, 1L, BigDecimal.TEN);
		when(service.confirmPayment(anyLong())).thenReturn(mockValueReturned);

		// act
		var result = mvc.perform(patch("/payments/1")
			.accept(MediaType.APPLICATION_JSON)
			).andReturn().getResponse();

		// assert
		assertEquals(HttpStatus.OK.value(), result.getStatus());
	}
}