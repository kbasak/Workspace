package com.service.coupon.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.coupon.model.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);

}
