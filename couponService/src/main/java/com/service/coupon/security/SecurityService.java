package com.service.coupon.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityService {
	boolean login(String userName, String password, HttpServletRequest request, HttpServletResponse response);

}
