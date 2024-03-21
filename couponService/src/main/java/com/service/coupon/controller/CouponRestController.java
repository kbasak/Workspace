package com.service.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.coupon.model.Coupon;
import com.service.coupon.repo.CouponRepo;

@RestController
public class CouponRestController {

	@Autowired
	private CouponRepo couponRepo;

	@PostMapping("/create")
	public Coupon create(@RequestBody Coupon coupon) {
		return couponRepo.save(coupon);
	}

	@GetMapping("/getCode/{code}")
	public Coupon getCoupon(@PathVariable String code) {
		return couponRepo.findByCode(code);
	}
}
