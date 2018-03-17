package com.imran.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	
	BaseService baseServicer = new BaseService();

	// Called before handler method invocation
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
		System.out.println("Session Data:"+req.getSession().getAttribute("loginUser"));
		
		if (baseServicer.getCurrentUserInfo(req.getSession()) == null) {
			res.sendRedirect(req.getContextPath() + "/auth/login");
			return false;
		}
		return true;
	}
	
	//
	// @Override
	// public void postHandle(HttpServletRequest request,
	//   HttpServletResponse response, Object handler,
	//   ModelAndView modelAndView) throws Exception {
	//  System.out.println("Calling postHandle");
	//  super.postHandle(request, response, handler, modelAndView);
	// }
	//
	// @Override
	// public void afterCompletion(HttpServletRequest request,
	//   HttpServletResponse response, Object handler, Exception ex)
	//   throws Exception {
	//  System.out.println("Calling afterCompletion");
	//  super.afterCompletion(request, response, handler, ex);
	// }
}
