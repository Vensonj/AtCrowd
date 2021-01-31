package com.wen.crowd.mvc.interceptor;

import com.wen.crowd.constant.CrowdConstant;
import com.wen.crowd.entity.Admin;
import com.wen.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author wen
 * @create 2021 1月 19 星期二 21:30
 * @description 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		// 1、通过request对象获取Session对象
		HttpSession session = request.getSession();
		// 2、尝试从Session域中获取Admin对象
		Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
		// 3、判断Admin对象是否为空
		if (admin == null) {
			// 4、如果为空返回false或者抛出AccessForbiddenException异常
			// return false;
			throw new AccessForbiddenException(CrowdConstant.MESSAGE_LOGIN_FORBIDDEN);
		}
		// 5、如果不为空，返回true，放行通过
		return true;

	}


}
