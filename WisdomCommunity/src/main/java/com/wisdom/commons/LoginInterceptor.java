package com.wisdom.commons;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.wisdom.model.Admin;
import com.wisdom.model.Owner;

public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI().trim().split("/")[2];
		System.out.println(url);
		List<String> commonURL = ResourcesUtil.getKeyList("commonURL");
		for (String open_url : commonURL) {
			if (url.indexOf(open_url) >= 0) {
				return true;
			}
		}
		List<String> ownerURL = ResourcesUtil.getKeyList("ownerURL");
		List<String> personnelURL = ResourcesUtil.getKeyList("personnelURL");
		HttpSession session = request.getSession();
		Owner activeUser = (Owner) session.getAttribute("owner");
		Admin activeAdmin = (Admin) session.getAttribute("admin");
		if (activeUser != null && activeUser.getOwnerLevel() == 1) {
			for (String owner_url : ownerURL) {
				if (url.indexOf(owner_url) >= 0) {
					return true;
				}
			}
		} else if (activeAdmin != null && activeAdmin.getAdminLevel() == 2) {
			for (String close_url : personnelURL) {
				if (url.indexOf(close_url) >= 0) {
					return true;
				}
			}
		}

		request.getRequestDispatcher("indexPage").forward(request, response);
		return false;
	}

}
