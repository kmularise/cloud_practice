package com.cloud.practice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.practice.service.ProductService;

import com.cloud.practice.dto.ProductAddRequest;
import com.cloud.practice.dto.ProductAddResponse;
import com.cloud.practice.dto.ProductPurchaseRequest;
import com.cloud.practice.dto.ProductPurchaseResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {
	private final ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<ProductAddResponse> add(@RequestBody ProductAddRequest productAddRequest) {
		ProductAddResponse productAddResponse = productService.create(productAddRequest);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(productAddResponse);
	}

	@PutMapping("/product/{id}")
	public ProductPurchaseResponse purchase(@PathVariable Long id, @RequestBody ProductPurchaseRequest productPurchaseRequest) {
		return productService.decreaseQuantity(id, productPurchaseRequest.getQuantity());
	}

}
