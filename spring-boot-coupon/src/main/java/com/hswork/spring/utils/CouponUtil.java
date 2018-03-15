package com.hswork.spring.utils;

import java.util.Random;

import org.springframework.util.StringUtils;

public class CouponUtil {
	
	/**
	 * 회원ID(시퀀스) 기반 랜덤 쿠폰번호 생성
	 * @param id
	 * @return
	 */
	public static String getCouponNum() {

		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		
		// 랜덤번호 16자리 설정
		for(int i=0; i<16; i++) {
			int rIndex = random.nextInt(3);
			
			switch(rIndex) {
			case 0:
				// a-z (ASCII: 97~122)
				sb.append((char) ((int) (random.nextInt(26)) + 97));
				break;
			case 1: 
				// a-z (ASCII: 65~90)
				sb.append((char) ((int) (random.nextInt(26)) + 65));
				break;
			case 2:
				sb.append(random.nextInt(10));
				break;
			}
		}
		
		return makeCouponForm(sb.toString());
	}
	
	/**
	 * 쿠폰코드 하이픈 삽입
	 * @param orgCouponNo
	 * @return
	 */
	public static String makeCouponForm(String couponId) {
		
		if(StringUtils.isEmpty(couponId)) {
			return couponId;
		}
		
		String regex = "(\\w{4})(\\w{4})(\\w{4})(\\w{4})";
		return couponId.replaceAll(regex, "$1-$2-$3-$4");
	}
}
