package com.hswork.spring.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hswork.spring.domain.Coupon;
import com.hswork.spring.exception.AlreadyIssueException;
import com.hswork.spring.repository.CouponRepository;
import com.hswork.spring.utils.CouponUtil;

@Service
public class CouponService {
	
	private static final Logger log = LoggerFactory.getLogger(CouponService.class);
	
	@Autowired
	CouponRepository repo;
	
	public void insertDefaultData() throws ParseException{
		log.info("### Default 데이터 생성");
		
		StringBuilder email = new StringBuilder();
		
		for(int i=1000; i<1122; i++) {
			email.append("user");
			email.append(i);
			email.append("@test.com");
			repo.save(new Coupon(email.toString(), CouponUtil.getCouponNum()));
			
			email.setLength(0);
		}
		
	}
	
	/**
	 * 쿠폰 등록
	 * @param coupon
	 * @return 
	 */
	public Coupon insertData(Coupon coupon) {
		
		// 이메일 중복 검증 
		if(isDuplicatedEmail(coupon.getEmail())) {
			throw new AlreadyIssueException("동일한 Email 정보가 존재합니다");
		}
		
		// 쿠폰번호 발행 및 중복 검증
		String couponId = CouponUtil.getCouponNum();
		while(isDuplicatedCouponId(couponId)) {
			couponId = CouponUtil.getCouponNum();
		}
		
		// 회원 정보 저장 
		Coupon insertCoupon = new Coupon();
		insertCoupon.setEmail(coupon.getEmail());
		insertCoupon.setCouponId(couponId);
		
		insertCoupon = repo.save(insertCoupon);
		log.info(insertCoupon.toString());
		
		return insertCoupon;
	}
	
	/**
	 * 쿠폰 목록 조회 
	 * @return 
	 */
	public List<Coupon> getCouponList() {
		return repo.findAll();
	}
	
	/**
	 * 쿠폰 목록 조회 
	 * @return 
	 */
	public Page<Coupon> getCouponPage(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	/**
	 * 이메일 주소 중복 검증
	 * @param email
	 * @return 
	 */
	public boolean isDuplicatedEmail(String email) {
		
		Long dupCnt = repo.countByEmail(email);
		
		if(dupCnt > 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 쿠폰ID 중복 검증
	 * @param couponId
	 * @return
	 */
	public boolean isDuplicatedCouponId(String couponId) {
		
		Long dupCnt = repo.countByCouponId(couponId);
		
		if(dupCnt > 0) {
			return true;
		}
		
		return false;
	}

}
