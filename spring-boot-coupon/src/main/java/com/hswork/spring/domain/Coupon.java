package com.hswork.spring.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hswork.spring.utils.JsonDateSerializer;

@Entity
public class Coupon {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String email;
	private String couponId;
	
	private Date created = new Date();
	
	@Transient
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Coupon(String email, String couponId) throws ParseException{
		this.email = email;
		this.couponId = couponId;
	}
	
	public Coupon(String email) throws ParseException{
		this.email = email;
	}
	
	public Coupon(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@JsonIgnore
	public String getCreatedAsShort() {
		return format.format(created);
	}
	
	public String toString() {
		StringBuilder value = new StringBuilder("Coupon (");
		value.append("Id: ");
		value.append(id);
		value.append(", Email: ");
		value.append(email);
		value.append(", 쿠폰번호: ");
		value.append(couponId);
		value.append(", 발급일자: ");
		value.append(getCreatedAsShort());
		value.append(")");
		
		return value.toString();
	}
	
	
}
