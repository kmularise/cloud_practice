package com.cloud.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cloud.practice.domain.Product;

import jakarta.persistence.LockModeType;
import jakarta.persistence.NamedQuery;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from Product p where p.id =:id")
	Optional<Product> findByIdPessimisticLock(@Param("id") Long id);

	@Lock(LockModeType.OPTIMISTIC)
	@Query("select p from Product p where p.id =:id")
	Optional<Product> findByOptimisticLock(@Param("id") Long id);
}
