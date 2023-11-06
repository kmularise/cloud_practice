package com.cloud.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.cloud.practice.domain.Product;
import com.cloud.practice.exception.BusinessException;
import com.cloud.practice.repository.ProductRepository;

import com.cloud.practice.dto.ProductAddRequest;
import com.cloud.practice.dto.ProductAddResponse;

@SpringBootTest
class ProductServiceTest {
	@Autowired
	ProductService productService;
	@Autowired
	ProductRepository productRepository;

	@DisplayName("낙관적 락 : 충돌 시 예외 발생")
	@Test
	void checkQuantityPositiveOrZero() throws InterruptedException, ExecutionException {
		ProductAddResponse response = productService.create(new ProductAddRequest("test", 1000L, 100L));
		List<Future<Boolean>> futures = new ArrayList<>();
		int count = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		CountDownLatch countDownLatch = new CountDownLatch(count);
		for (int idx = 0; idx < count ; idx++) {
			Future<Boolean> future = executorService.submit(() -> {
				boolean failure = false;
				try {
					productService.decreaseQuantity(response.getId(), 1L);
					countDownLatch.countDown();
				} catch (ObjectOptimisticLockingFailureException e) {
					failure = true;
				} finally {
					countDownLatch.countDown();
				}
				return failure;
			});
			futures.add(future);
		}
		countDownLatch.await();
		boolean hasOptimisticLockFailure = false;
		for (Future<Boolean> hasException : futures) {
			if (hasException.get()) {
				hasOptimisticLockFailure = true;
				break ;
			}
		}
		Assertions.assertThat(hasOptimisticLockFailure).isTrue();
	}

	@DisplayName("낙관적 락 : 충돌 시 재시도")
	@Test
	void validWithOptimisticRetry() throws InterruptedException {
		ProductAddResponse response = productService.create(new ProductAddRequest("test", 1000L, 100L));
		int count = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		CountDownLatch countDownLatch = new CountDownLatch(count);
		for (int idx = 0; idx < count ; idx++) {
			executorService.execute(() -> {
				productService.decreaseQuantityWithRetry(response.getId(), 1L);
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
	    Product product = productRepository.findById(response.getId()).orElseThrow(BusinessException::new);
		Assertions.assertThat(product.getStockQuantity()).isEqualTo(0L);
	}
}
