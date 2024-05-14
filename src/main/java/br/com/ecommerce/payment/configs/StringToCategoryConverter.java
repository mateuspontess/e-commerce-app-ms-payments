package br.com.ecommerce.payment.configs;

import org.springframework.core.convert.converter.Converter;

import br.com.ecommerce.payment.model.PaymentStatus;

public class StringToCategoryConverter implements Converter<String, PaymentStatus>{

	@Override
	public PaymentStatus convert(String source) {
		try {
			return PaymentStatus.fromString(source);
		
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}