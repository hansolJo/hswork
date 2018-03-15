package com.hswork.spring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hswork.spring.domain.Coupon;
import com.hswork.spring.service.CouponService;

@RestController
public class CouponController {
	
	private static final Logger log = LoggerFactory.getLogger(CouponController.class);
	
	private static final String VIEW_INDEX = "index";
	
	@Autowired
	CouponService couponService;
	
	@RequestMapping(value="/", method=RequestMethod.GET )
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName(VIEW_INDEX);
		modelAndView.addObject("coupons", couponService.getCouponList());
		return modelAndView;
	}
	
	@RequestMapping(value="/coupons", method=RequestMethod.GET ) 
	public ResponseEntity<Page<Coupon>> getCouponList(
			@PageableDefault(sort={"id"}, direction=Direction.DESC, size=10) Pageable pageable) {
		return new ResponseEntity<Page<Coupon>>(couponService.getCouponPage(pageable), HttpStatus.OK);
	}
	
	@RequestMapping(value="/coupons", method=RequestMethod.POST ) 
	public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
		log.info("### 쿠폰등록 요청 : " + coupon.getEmail());
		Coupon crtCoupon = couponService.insertData(coupon);
		
		return new ResponseEntity<Coupon>(crtCoupon, HttpStatus.OK);
	}
}
