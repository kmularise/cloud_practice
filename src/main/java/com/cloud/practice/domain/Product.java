package com.cloud.practice.domain;

import java.math.BigInteger;

import com.cloud.practice.exception.BusinessException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	private Long version;
	String name;
	Long price;
	Long stockQuantity;

	public void decreaseQuantity(Long pickedQuantity) {
		validateQuantity(pickedQuantity);
		stockQuantity -= pickedQuantity;
	}

	private void validateQuantity(Long pickedQuantity) {
		if (stockQuantity - pickedQuantity < 0) {
			throw new BusinessException();
		}
	}

	public Product(String name, Long price, Long stockQuantity) {
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public void updateQuantity(Long pickedQuantity) {
		stockQuantity = pickedQuantity;
	}
}
