package com.cloud.practice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.practice.domain.Product;
import com.cloud.practice.exception.BusinessException;
import com.cloud.practice.global.aop.Retry;
import com.cloud.practice.repository.ProductRepository;

import com.cloud.practice.dto.ProductAddRequest;
import com.cloud.practice.dto.ProductAddResponse;
import com.cloud.practice.dto.ProductPurchaseResponse;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final EntityManager entityManager;

	public ProductAddResponse create(ProductAddRequest productRequest) {
		Product product = productRequest.toProduct();
		productRepository.save(product);
		return new ProductAddResponse(product);
	}
	@Transactional
	public ProductPurchaseResponse decreaseQuantity(Long id, Long pickedQuantity) {
		Product product = productRepository.findById(id).orElseThrow(BusinessException::new);
		product.decreaseQuantity(pickedQuantity);
		return new ProductPurchaseResponse(product.getStockQuantity());
	}

	@Retry
	@Transactional
	public ProductPurchaseResponse decreaseQuantityWithRetry(Long id, Long pickedQuantity) {
		return decreaseQuantity(id, pickedQuantity);
	}
}
