package com.service.product.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.service.product.dto.Coupon;
import com.service.product.model.Product;
import com.service.product.repo.ProductRepo;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponServiceURL;

	@PostMapping("/saveProduct")
	public Product saveProduct(@RequestBody Product product,
			@RequestHeader(value = "Authorization") String authorizationHeader) {
		// Coupon coupon = restTemplate.getForObject(couponServiceURL
		// +product.getCouponCode(), Coupon.class);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorizationHeader);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<Coupon> couponResponse = restTemplate.exchange(couponServiceURL + product.getCouponCode(),
				HttpMethod.GET, request, Coupon.class);
		Coupon coupon = couponResponse.getBody();
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return productRepo.save(product);
	}

	@GetMapping("/viewProduct/{id}")
	public Optional<Product> getProduct(@PathVariable Long id){
			
		return productRepo.findById(id);

	}

}
