package com.hswork.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hswork.spring.domain.Coupon;

@Transactional
@RepositoryRestResource(collectionResourceRel = "coupons", path="coupons")
public interface CouponRepository extends JpaRepository<Coupon, Long>{

	List<Coupon> findByEmail(String word);
	
	Long countByEmail(String word);
	
	Long countByCouponId(String word);
	
	Page<Coupon> findAll(Pageable pageable);
	
	
}
