package com.service.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.service.product.dto.Coupon;
import com.service.product.model.Product;
import com.service.product.repo.ProductRepo;


@Controller
public class ProductViewController {

	@Autowired
	private ProductRepo repo;
	
	@Autowired
	private RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponServiceURL;

	@GetMapping("/showCreateProduct")
	public String showCreateCoupon() {
		return "createProduct";
	}
	
	@PostMapping("/saveProduct")
	public String save(Product product,@RequestHeader(value = "Authorization") String authorizationHeader) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorizationHeader);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<Coupon> couponResponse = restTemplate.exchange(couponServiceURL + product.getCouponCode(),
				HttpMethod.GET, request, Coupon.class);
		Coupon coupon = couponResponse.getBody();
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		repo.save(product);
		
		return "createResponse";
	}
	
	@GetMapping("/showGetProduct")
	public String showGetProduct() {
		return "getProduct";
	}
	
	@PostMapping("/getProduct")
	public ModelAndView getProduct(String code) {
		ModelAndView mav = new ModelAndView("productDetails");
		System.out.println("Product Id: "+code);
		long id=Long.parseLong(code);
		System.out.println("Product Id: "+id);
		System.out.println(repo.findById(id).get());
		mav.addObject(repo.findById(id).get());
		return mav;
	}
}
