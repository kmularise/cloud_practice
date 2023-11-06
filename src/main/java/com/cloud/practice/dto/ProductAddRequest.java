package com.cloud.practice.dto;

import com.cloud.practice.domain.Product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductAddRequest {
	private String name;
	private Long price;
	private Long stockQuantity;

	public Product toProduct() {
		return new Product(name, price, stockQuantity);
	}
}
