package com.cloud.practice.dto;

import com.cloud.practice.domain.Product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductAddResponse {
	private Long id;

	public ProductAddResponse(Product product) {
		this.id = product.getId();
	}
}
