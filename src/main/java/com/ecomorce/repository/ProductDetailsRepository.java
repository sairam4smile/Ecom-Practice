package com.ecomorce.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomorce.model.ProductDetails;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
	
	public Set<ProductDetails> findByProductName(String productName);

	@Query(value = "SELECT * FROM PRODUCT_DETAILS  u WHERE u.PRODUCT_CATEGORY_ID = ?1", nativeQuery = true)
	public Set<ProductDetails> findAllProductsNative(Long  productCategoryName);

}
